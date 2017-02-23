package com.redballgolf.golfSG.RoundOfGolf;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.redballgolf.golfSG.Common.BaseActivity;
import com.redballgolf.golfSG.Course.PinsCoordinates;
import com.redballgolf.golfSG.GPS.Coordinates;
import com.redballgolf.golfSG.ObserverSubject.Observer;
import com.redballgolf.golfSG.ObserverSubject.Subject;
import com.redballgolf.golfSG.R;
import com.redballgolf.golfSG.SharedPreferences.Preferences;

import java.util.ArrayList;
import java.util.List;

public class ShotInputScreen extends BaseActivity implements Observer, AdapterView.OnItemSelectedListener{
    public static long POLLING_FREQUENCY = 0;//milliseconds.
    private static float MIN_DISTANCE = 1;//meters

    public static int roundID;
    private double latitude;
    private double longitude;
    private double accuracy;
    public static String place;
    private Button mydriver;
    private Button myiron;
    private Button fairway;
    private Button rough;
    private Button trees;
    private Button bunker;
    private Button penalty;
    private Button onGreen;
    public static Double distToGreen;
    public static String LoggedInId;
    private int numberOfPutts;
    ImageView accuracyView;
    TextView holeNumberTextview;
    TextView accuracyTextView;
    Round round;
    Hole hole;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shot_input_screen);

        Coordinates.registerObserver(this);

        LoggedInId = Preferences.getPreferences(this, "loginID");
        roundID = Preferences.getPreferencesInt(this, "roundID");

        Boolean isNewRound = Preferences.getPreferencesBoolean(this, "isNewRound");
        if(isNewRound){
            round = new Round(roundID);
            hole = new Hole(round);
            Preferences.insertBoolean("isNewRound", false, this);
        }

        accuracyView = (ImageView)findViewById(R.id.gps_image);
        accuracyTextView = (TextView)findViewById(R.id.accuracy);

        holeNumberTextview = (TextView) findViewById(R.id.hole_number);
        displayHoleAndShotNumber();


        disableButtonsUntilGpsAccuracyBetterThan3();
        displayDistanceToGreen();
    }//onCreate


    public void recordShot(View view)
    {
        place = getClickedButton(view);

        //AlertDialog to check if user actually wants to submit shot
        //and its not a case of clicking button by accident.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure you want to submit this shot?");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //If user clicks 'ok' then run this code to send lat and long
                        //to addProduct method which is located in the DatabaseHelper class.
                        Shot shot = new Shot(hole, place);
                        shot.addShotToSqlite(ShotInputScreen.this);
                        shot.addShotToHolesShotList();
                        displayHoleAndShotNumber();
                    }
                });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private void displayDistanceToGreen(){
        distToGreen = CalculateDistance.distanceIs(latitude, longitude, PinsCoordinates.getPinLatitude(), PinsCoordinates.getPinLongitude());
        Integer distToGreenInt = distToGreen.intValue();
        TextView distanceToGreenTV = (TextView) findViewById(R.id.distanceToGreen);
        distanceToGreenTV.setText("Distance to Green: " + distToGreenInt + " yards.");
    }

    private void displayHoleAndShotNumber(){
        holeNumberTextview.setText("Hole: " + hole.getHoleNumber() + " - Shot: " + Shot.getShotNumber());
    }
    public void goToGreen(View view){
        Intent intentGoToGreenScreen = new Intent(ShotInputScreen.this,OnTheGreen.class);
        intentGoToGreenScreen.putExtra("Hole", hole);
        startActivity(intentGoToGreenScreen);

    }

    @Override
    public void update(double currentLatitude, double currentLongitude, double accuracy) {
        Log.i("TAG", "ShotInputScreen - update from observer received.");
        this.latitude = currentLatitude;
        this.longitude = currentLongitude;
        this.accuracy = accuracy;
        if(accuracy <= 30){
            enableButtons(accuracy);
        }else{
            accuracyTextView.setText("Accuracy is: " + accuracy);//comment out...this is for testing
            disableButtonsUntilGpsAccuracyBetterThan3();
        }
        displayDistanceToGreen();
    }


    private void enableButtons(double accuracy) {
        accuracyTextView.setText("Accuracy is: " + accuracy);
        accuracyView = (ImageView)findViewById(R.id.gps_image);

            POLLING_FREQUENCY = 1000;
            accuracyView.setImageResource(R.drawable.gps_green);

            onGreen.setEnabled(true);
            onGreen.getBackground().setColorFilter(null);

            mydriver.setEnabled(true);
            mydriver.getBackground().setColorFilter(null);

            myiron.setEnabled(true);
            myiron.getBackground().setColorFilter(null);

            fairway.setEnabled(true);
            fairway.getBackground().setColorFilter(null);

            rough.setEnabled(true);
            rough.getBackground().setColorFilter(null);

            trees.setEnabled(true);
            trees.getBackground().setColorFilter(null);

            bunker.setEnabled(true);
            bunker.getBackground().setColorFilter(null);
    }

    private void disableButtonsUntilGpsAccuracyBetterThan3(){
        accuracyView.setImageResource(R.drawable.gps_red);
        onGreen = (Button)findViewById(R.id.flag);
        onGreen.setEnabled(false);
        onGreen.getBackground().setColorFilter(Color.DKGRAY, PorterDuff.Mode.MULTIPLY);

        mydriver = (Button)findViewById(R.id.driver);
        mydriver.setEnabled(false);
        mydriver.getBackground().setColorFilter(Color.DKGRAY, PorterDuff.Mode.MULTIPLY);

        myiron = (Button)findViewById(R.id.iron);
        myiron.setEnabled(false);
        myiron.getBackground().setColorFilter(Color.DKGRAY, PorterDuff.Mode.MULTIPLY);

        fairway = (Button)findViewById(R.id.fairway);
        fairway.setEnabled(false);
        fairway.getBackground().setColorFilter(Color.DKGRAY, PorterDuff.Mode.MULTIPLY);

        rough = (Button)findViewById(R.id.right_rough);
        rough.setEnabled(false);
        rough.getBackground().setColorFilter(Color.DKGRAY, PorterDuff.Mode.MULTIPLY);

        trees = (Button)findViewById(R.id.right_trees);
        trees.setEnabled(false);
        trees.getBackground().setColorFilter(Color.DKGRAY, PorterDuff.Mode.MULTIPLY);

        bunker = (Button)findViewById(R.id.right_bunker);
        bunker.setEnabled(false);
        bunker.getBackground().setColorFilter(Color.DKGRAY, PorterDuff.Mode.MULTIPLY);
    }


    private String getClickedButton(View view){
        switch(view.getId())
        {
            case R.id.driver:
                place = "tee_shot_driver";
                break;
            case R.id.iron:
                place = "tee_shot_iron";
                break;
            case R.id.fairway:
                place = "fairway";
                break;
            case R.id.right_trees:
                place = "rightTrees";
                break;
            case R.id.right_rough:
                place = "rightRough";
                break;
            case R.id.right_bunker:
                place = "rightBunker";
                break;
            case R.id.flag:
                place = "green";
                break;
        }//switch
        return place;
    }

    private void addHandicapChoiceSpinner(){
        Log.i("TAG","NewRound - Spinner added");
        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        List<Integer> categories = new ArrayList<>();
        categories.add(0);
        categories.add(1);
        categories.add(2);
        categories.add(3);
        categories.add(4);
        categories.add(5);
        categories.add(6);



        // Creating adapter for spinner
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        numberOfPutts = (Integer)parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //put code here.
    }

}//class

