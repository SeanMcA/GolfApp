package com.redballgolf.golfSG.RoundOfGolf;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.redballgolf.golfSG.Common.BaseActivity;
import com.redballgolf.golfSG.GPS.Coordinates;
import com.redballgolf.golfSG.ObserverSubject.Observer;
import com.redballgolf.golfSG.R;
import com.redballgolf.golfSG.SQLite.DatabaseHelper;
import com.redballgolf.golfSG.SharedPreferences.Preferences;

public class ShotInputScreen extends BaseActivity implements Observer{

    public static long POLLING_FREQUENCY = 0;//milliseconds.
    private static float MIN_DISTANCE = 1;//meters

    public static int hole_counter;
    public static int shot_counter = 1;
    public static int roundID;
    private double latitude;
    private double longitude;
    private double accuracy;
    public static String place;
    private static Button mydriver;
    private static Button myiron;
    private static Button fairway;
    private static Button rough;
    private static Button trees;
    private static Button bunker;
    private static Button penalty;
    private static Button onGreen;
    public static Double distToGreen;
    public static String LoggedInId;
    public static LocationManager mLocationManager;
    public static LocationListener mLocationListener;
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreferences;
    ImageView accuracyView;
    TextView holeNumberTextview;

    private static final String TAG = "TEST";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Log.i(TAG, "MAIN - onCreate - started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shot_input_screen);

        Coordinates coordinates = new Coordinates();
        coordinates.registerObserver(this);

        LoggedInId = Preferences.getPreferences(this, "loginID");
        roundID = Preferences.getPreferencesInt(this, "roundID");

        shot_counter = Shot.getShotNumber();
        //Log.i(TAG, "SIS shot_counter is: " + shot_counter);
        hole_counter = Hole.getHoleNumber();

        disableButtonsUntilGpsAccuracyBetterThan3();

        accuracyView = (ImageView)findViewById(R.id.gps_image);
        holeNumberTextview = (TextView) findViewById(R.id.hole_number);
        holeNumberTextview.setText("Hole: " + hole_counter + " - Shot: " + shot_counter);

        displayDistanceToGreen();

    }//onCreate


    /**
     * This method adds data to the database regarding a shot that was hit.
     * When the button is clicked, the id of the button is checked to see which button it was,
     * then depending on which button was clicked the String variable 'place' is set to the
     * correct value. Then an alert box is displayed to verify that the user wants to submit a shot
     * and that its not a case of a button being clicked in error.
     * If the user confirms that they want to submit the shot then
     * the latitude and longitude are retrieved along with the hole number and the shot number
     * as well as where the shot was hit from. This information is then sent to the addShot method
     * in the DatabaseHelper class.
     * @param view The view that was clicked.
     */
    public void buttonOnClick(View view)
    {
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
            case R.id.green:
                place = "green";
                break;
        }//switch


        //AlertDialog to check if user actually wants to submit shot
        //and its not a case of clicking button by accident.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure you want to submit this shot?");

        //builder.setMessage("Some message...")
        builder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //If user clicks 'ok' then run this code to send lat and long
                        //to addProduct method which is located in the DatabaseHelper class.
                        Shot shot = new Shot();
                        Shot.addShotToSqlite(this, latitude,longitude, place, roundID);
                        DatabaseHelper dbHandler = new DatabaseHelper(ShotInputScreen.this);

                        dbHandler.addShotToDB(Coordinates.getLatitude(), Coordinates.getLongitude(), place, hole_counter, shot_counter, roundID);
                        shot_counter++;
                        holeNumberTextview.setText("Hole: " + hole_counter + " - Shot: "+ shot_counter);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }//buttonOnClick


    private void displayDistanceToGreen(){
        distToGreen = CalculateDistance.distanceIs(latitude, longitude, PinsCoordinates.getPinLatitude(), PinsCoordinates.getPinLongitude());
        Integer distToGreenInt = distToGreen.intValue();
        TextView distanceToGreenTV = (TextView) findViewById(R.id.distanceToGreen);
        distanceToGreenTV.setText("Distance to Green: " + distToGreenInt + " yards.");
    }

    public void goToGreen(View view){
//        Intent intentGoToGreenScreen = new Intent(ShotInputScreen.this,OnGreen.class);
//        //Log.i(TAG, "goToGreen started");
//        MIN_DISTANCE = 1;
//        intentGoToGreenScreen.putExtra("roundID", roundID);
//        intentGoToGreenScreen.putExtra("shot_number", shot_counter);
//        intentGoToGreenScreen.putExtra("hole_number", hole_counter);
//        //Log.i(TAG, "Sending to GREEN sn: " + shot_counter);
//        startActivity(intentGoToGreenScreen);

    }

    @Override
    public void update(double currentLatitude, double currentLongitude, double accuracy) {
        Log.i("TAG", "ShotInputScreen - update from observer received.");
        this.latitude = currentLatitude;
        this.longitude = currentLongitude;
        this.accuracy = accuracy;
        if(accuracy <=5){
            enableButtons(accuracy);
        }
    }


    private void enableButtons(double accuracy) {
        TextView accuracyTextView = (TextView)findViewById(R.id.accuracy);
        accuracyTextView.setText("Accuracy is: " + accuracy);
        ImageView accuracyView = (ImageView)findViewById(R.id.gps_image);

        if(accuracy <= 5){
            POLLING_FREQUENCY = 1000;
            accuracyView.setImageResource(R.drawable.gps_green);

            penalty.setEnabled(true);
            penalty.getBackground().setColorFilter(null);

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
    }

    private void disableButtonsUntilGpsAccuracyBetterThan3(){
        accuracyView.setImageResource(R.drawable.gps_red);
        onGreen = (Button)findViewById(R.id.green);
        onGreen.setEnabled(false);
        onGreen.getBackground().setColorFilter(Color.DKGRAY, PorterDuff.Mode.MULTIPLY);

        penalty = (Button)findViewById(R.id.out_of_bounds);
        penalty.setEnabled(false);
        penalty.getBackground().setColorFilter(Color.DKGRAY, PorterDuff.Mode.MULTIPLY);

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
}//class

