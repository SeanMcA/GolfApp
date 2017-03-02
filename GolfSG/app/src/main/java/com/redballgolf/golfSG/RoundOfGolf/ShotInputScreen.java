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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.redballgolf.golfSG.Common.BaseActivity;
import com.redballgolf.golfSG.Course.PinsCoordinates;
import com.redballgolf.golfSG.GPS.Coordinates;
import com.redballgolf.golfSG.ObserverSubject.Observer;
import com.redballgolf.golfSG.R;
import com.redballgolf.golfSG.SharedPreferences.Preferences;

public class ShotInputScreen extends BaseActivity implements Observer, AdapterView.OnItemSelectedListener{
    public static long POLLING_FREQUENCY = 0;//milliseconds.
    private static float MIN_DISTANCE = 1;//meters

    private int roundID;
    private double latitude;
    private double longitude;
    private double accuracy;
    private String place;
    private Button mydriver;
    private Button myiron;
    private Button fairway;
    private Button rough;
    private Button trees;
    private Button bunker;
    private Button penalty;
    private Button onGreen;
    private double distToGreen;
    private String LoggedInId;
    private int numberOfPutts;
    public static final String TEESHOTDRIVER = "tee";
    public static final String TEESHOTIRON = "tee";
    public static final String FAIRWAY = "Fairway";
    public static final String ROUGH = "Rough";
    public static final String SAND = "Sand";
    public static final String TREES = "Trees";
    public static final String PENALTY = "Penalty";
    public static final String RECOVERY = "Recovery";
    public static final String PUTT = "First putt";
    private ImageView accuracyView;
    private TextView holeNumberTextview;
    private TextView accuracyTextView;
    private Round round;
    Hole hole;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shot_input_screen);

        Coordinates.registerObserver(this);

        LoggedInId = Preferences.getPreferences(this, "loginID");
        roundID = Preferences.getPreferencesInt(this, "roundID");

        Intent intent = getIntent();
        round = intent.getExtras().getParcelable("Round");

        Boolean isNewRound = Preferences.getPreferencesBoolean(this, "isNewRound");
        if(isNewRound){
            round = new Round(roundID);
            hole = new Hole();
            round.addHoleToRound(hole);
            Preferences.insertBoolean("isNewRound", false, this);
        }else{
            hole = new Hole();
            round.addHoleToRound(hole);
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

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure you want to submit this shot?");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                            Shot shot = new Shot(place);
                            shot.addShotToSqlite(ShotInputScreen.this);
                            hole.addShotToList(shot);
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

    public void goToHoleSummary(View view){
        Flag flag = new Flag();
        ShotScore.calculateEachShotOnThis(hole, flag);
        ShotScore.calculateFinalShotScore(hole);
        Intent goToHoleSummary = new Intent(ShotInputScreen.this,HoleSummary.class);
        goToHoleSummary.putExtra("Hole", hole);
        goToHoleSummary.putExtra("Round", round);
        Log.i("TAG","ShotInputScreen - shot scores calculated");
        startActivity(goToHoleSummary);
    }

    private void displayDistanceToGreen(){
        distToGreen = CalculateDistance.distanceIs(latitude, longitude, PinsCoordinates.getPinLatitude(), PinsCoordinates.getPinLongitude());
        int distToGreenInt = (int)distToGreen;
        TextView distanceToGreenTV = (TextView) findViewById(R.id.distanceToGreen);
        distanceToGreenTV.setText("Distance to Green: " + distToGreenInt + " yards.");
    }

    private void displayHoleAndShotNumber(){
        holeNumberTextview.setText("Hole: " + hole.getHoleNumber() + " - Shot: " + Shot.getShotNumber());
    }

    @Override
    public void update(double currentLatitude, double currentLongitude, double accuracy) {
        //Log.i("TAG", "ShotInputScreen - update from observer received.");
        this.latitude = currentLatitude;
        this.longitude = currentLongitude;
        this.accuracy = accuracy;
        if(accuracy <= 50){
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

        rough = (Button)findViewById(R.id.rough);
        rough.setEnabled(false);
        rough.getBackground().setColorFilter(Color.DKGRAY, PorterDuff.Mode.MULTIPLY);

        trees = (Button)findViewById(R.id.trees);
        trees.setEnabled(false);
        trees.getBackground().setColorFilter(Color.DKGRAY, PorterDuff.Mode.MULTIPLY);

        bunker = (Button)findViewById(R.id.bunker);
        bunker.setEnabled(false);
        bunker.getBackground().setColorFilter(Color.DKGRAY, PorterDuff.Mode.MULTIPLY);
    }


    private String getClickedButton(View view){
        switch(view.getId())
        {
            case R.id.driver:
                place = ShotInputScreen.TEESHOTDRIVER;
                break;
            case R.id.iron:
                place = ShotInputScreen.TEESHOTIRON;
                break;
            case R.id.fairway:
                place = ShotInputScreen.FAIRWAY;
                break;
            case R.id.trees:
                place = ShotInputScreen.TREES;
                break;
            case R.id.rough:
                place = ShotInputScreen.ROUGH;
                break;
            case R.id.bunker:
                place = ShotInputScreen.SAND;
                break;
            case R.id.penalty:
                place = ShotInputScreen.PENALTY;
                break;
            case R.id.firstPutt:
                place = ShotInputScreen.PUTT;
                break;
        }
        return place;
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

