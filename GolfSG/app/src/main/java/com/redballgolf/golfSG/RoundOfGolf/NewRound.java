package com.redballgolf.golfSG.RoundOfGolf;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.redballgolf.golfSG.Common.BaseActivity;
import com.redballgolf.golfSG.Common.GetDate;
import com.redballgolf.golfSG.Common.ToastMessage;
import com.redballgolf.golfSG.GPS.Coordinates;
import com.redballgolf.golfSG.R;
import com.redballgolf.golfSG.SQLite.DatabaseHelper;
import com.redballgolf.golfSG.SharedPreferences.Preferences;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class NewRound extends BaseActivity implements AdapterView.OnItemSelectedListener {
    ArrayList<HashMap<String, String>> CourseList;
    public static ListAdapter adapter;
    ArrayList<HashMap<String, String>> mylistOfCourses;
    Context context;
    TextView info;
    ListView listOfCoursesLV;
    private static String jsonData;
    public static String today;
    public static String today2;
    public static int roundID;
    private static String handicap;
    private static String courseID;
    public static String no = "no";
    private static String selectedCourseName;
    public static String[] courseIDsArray;
    public static String[] courseNamesArray;
    public static String[] coursePinsArray;
    public static ArrayList<String> pins;
    TextView selected_course;
    private Vibrator vibe;

    private static final String TAG = "TEST";
    public static String LoggedInId;

    private static final String FACILITY_NAME = "facility_name";
    private static final String COURSE_NAME = "name";

    public static String  h1lat;
    public static String  h1long;
    public static String  h2lat;
    public static String  h2long;
    public static String  h3lat;
    public static String  h3long;
    public static String  h4lat;
    public static String  h4long;
    public static String  h5lat;
    public static String  h5long;
    public static String  h6lat;
    public static String  h6long;
    public static String  h7lat;
    public static String  h7long;
    public static String  h8lat;
    public static String  h8long;
    public static String  h9lat;
    public static String  h9long;
    public static String  h10lat;
    public static String  h10long;
    public static String  h11lat;
    public static String  h11long;
    public static String  h12lat;
    public static String  h12long;
    public static String  h13lat;
    public static String  h13long;
    public static String  h14lat;
    public static String  h14long;
    public static String  h15lat;
    public static String  h15long;
    public static String  h16lat;
    public static String  h16long;
    public static String  h17lat;
    public static String  h17long;
    public static String  h18lat;
    public static String  h18long;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_round);
        context = this;

        selected_course = (TextView)findViewById(R.id.selected_course);
        info = (TextView) findViewById(R.id.info);
        LoggedInId = Preferences.getPreferences(this, "loginID");
        today2 = GetDate.todaysDateInSimpleFormat();
        vibe = (Vibrator) NewRound.this.getSystemService(Context.VIBRATOR_SERVICE);

        ToastMessage.displayLongToastMessage(context, "Please wait while we find Golf Courses in your location.");

        if(Coordinates.getAccuracy() <= 500){
            new RetrieveListOfCourses().execute();
        }
        addHandicapChoiceSpinner();
    }//onCreate


    private class RetrieveListOfCourses extends AsyncTask<Void, Void, Void>{
        ArrayList<HashMap<String, String>> CourseList;
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute(){
            Log.i("TAG", "GetListOfCourses started");
            pDialog = new ProgressDialog(context);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0){
            String url = "http://zelusit.com/androidGetCourses.php?latitude="
                    + Coordinates.getLatitude() + "&longitude=" + Coordinates.getLongitude();
            GetListOfCourses listOfCourses = new GetListOfCourses();
            jsonData = listOfCourses.retrieveCourses(url);
            mylistOfCourses = ExtractCourses.ParseJSON(jsonData);
            return null;
        }

        @Override
        protected void onPostExecute(Void data){

        }

    }//Retrieve class


    private void myAdapter(){
        adapter = new SimpleAdapter(
                NewRound.this, ExtractCoursesFromJsonString.CourseList,
                R.layout.course_list_layout, new String[]{FACILITY_NAME, COURSE_NAME}, new int[]{R.id.facility_name, R.id.course_name});
        //The new String above says which info goes in which TextView
        // Calling async task to get json
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocationListener);
//            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mLocationListener);
//        }
    }//onResume


    @Override
    protected void onPause() {
        super.onPause();
//        if ( ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ) {
//            mLocationManager.removeUpdates(mLocationListener);
//        }
    }//onPause



    /**
     * Spinner select
     * @param parent The parent view
     * @param view The view selected
     * @param position the position of the handicap clicked
     * @param id The id of the handicap clicked
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner handicap
        handicap = parent.getItemAtPosition(position).toString();

        // Showing selected spinner handicap
        //Log.i(TAG, "Selected handicap is: " + handicap);
        //Toast.makeText(parent.getContext(), "Selected: " + handicap, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {

    }




    /**
     * This method gets the current date and time as well as the name of the course
     * that the user input. It send this data to the addNewRoundToDB method
     * in the DatabaseHelper class. The getRound method is then called.
     * @param view The view that was clicked
     */
    public void putInfoInDB(View view){
        today = GetDate.todaysDate();
        String handicap = NewRound.handicap;

        DatabaseHelper dbHandler = new DatabaseHelper(NewRound.this);
        dbHandler.addNewRoundToDB(today, selectedCourseName, handicap, LoggedInId);

        getRoundID();
    }




    /**
     * This method is used to get the id of the round just created. This id will be
     * sent to the the ShotInputScreen activity and the id will be put into the SQLite database
     * to identify which round each shot is connected with. This roundID is then put in an
     * intent and the ShotInputScreen activity is started.
     */
    public void getRoundID(){
        try {
            Log.i(TAG, "getRoundID - started");
            SQLiteOpenHelper myTestDatabaseHelper = new DatabaseHelper(this);
            //We don’t need to write to the database so we’re using getReadableDatabase().
            SQLiteDatabase db = myTestDatabaseHelper.getReadableDatabase();
            //Use cursor to get number of rows where hole_counter equals X
            String query1 = "SELECT _id FROM round WHERE date = '" + today + "';";
            //Log.i(TAG, "Summary Query is: " + query1);
            Cursor cursor = db.rawQuery(query1, null);
            if (cursor.moveToFirst()) {
                roundID = cursor.getInt(0);
                Log.i(TAG, "roundId is: " + roundID);
            }
            cursor.close();
        }//try
        catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        Preferences.insertInt("roundID", roundID, this);
            Intent intentGoToShotInputScreen = new Intent(NewRound.this, ShotInputScreen.class);
            intentGoToShotInputScreen.putExtra("roundID", roundID);
            intentGoToShotInputScreen.putStringArrayListExtra("pinsArray", pins);
            startActivity(intentGoToShotInputScreen);



    }//getRoundID


    private void addHandicapChoiceSpinner(){
        Log.i("TAG","NewRound - Spinner added");
        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        List<String> categories = new ArrayList<>();
        categories.add("0");
        categories.add("1");
        categories.add("2");
        categories.add("3");
        categories.add("4");
        categories.add("5");
        categories.add("6");
        categories.add("7");
        categories.add("8");
        categories.add("9");
        categories.add("10");
        categories.add("11");
        categories.add("12");
        categories.add("13");
        categories.add("14");
        categories.add("15");
        categories.add("16");
        categories.add("17");
        categories.add("18");
        categories.add("19");
        categories.add("20");
        categories.add("21");
        categories.add("22");
        categories.add("23");
        categories.add("24");
        categories.add("25");
        categories.add("26");
        categories.add("27");
        categories.add("28");
        categories.add("29");
        categories.add("30");
        categories.add("31");
        categories.add("32");
        categories.add("33");
        categories.add("34");
        categories.add("35");
        categories.add("36");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }







   private void checkIfUserHasSelectedCourse(){
       if(CourseList != null) {
           listOfCoursesLV.setAdapter(adapter);
           listOfCoursesLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {

               @Override
               public void onItemClick(AdapterView<?> parent, View view,
                                       int position, long id) {
                   //Log.i(TAG, "League id from array is: " + leagueIDsArray[position]);

                   //Intent intentIndividualLeagues = new Intent(NewRound.this, ShotInputScreen.class);
                   //intentIndividualLeagues.putExtra("courseID", courseIDsArray[position]);
                   Log.i(TAG, "Course ID: " + courseIDsArray[position]);
                   courseID = courseIDsArray[position];
                   selectedCourseName = courseNamesArray[position];
                   //Log.i(TAG, "Sending LeagueId: " + leagueIDsArray[position]);
                   //Log.i(TAG, "Sending LeagueTYPE: " + leagueTypeArray[position]);
                   //startActivity(intentIndividualLeagues);
                   selected_course.setText("" + selectedCourseName);
                   vibe.vibrate(300);


                   //if course is mapped (there is data in the coursePinsArray at the position of the selected course)
                   //then create an ArrayList called pins. The comma seperated text is split at the commas
                   //and each value is put into an array element. The elements are then put into shared preferences.
                   //Mapped is 'yes' if the course is mapped and 'no' if it is not.

                   //REALLY NEED TO SLASH AND BURN HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                   if (coursePinsArray[position].isEmpty()) {
                       Log.i(TAG, "Course not mapped putting no in SP");
                       SharedPreferences sharedPreferences = PreferenceManager
                               .getDefaultSharedPreferences(NewRound.this);
                       SharedPreferences.Editor editor = sharedPreferences.edit();
                       editor.putString("mapped", no);
                       editor.putString("courseID", courseID);
                       editor.apply();
                   } else {
                       Log.i(TAG, "Course  mapped putting YES in SP");
                       pins = new ArrayList<>(Arrays.asList(coursePinsArray[position].split(",")));



                       SharedPreferences sharedPreferences = PreferenceManager
                               .getDefaultSharedPreferences(NewRound.this);
                       SharedPreferences.Editor editor = sharedPreferences.edit();

                       //Doesnt need to be put into shared prefs. Put in array
                       //pins[0] is hole 1 pin latitude
                       //pins[1] is hole 1 longitude
                       //etc.
                       editor.putString("h1lat", h1lat = pins.get(0));
                       editor.putString("h1long", h1long = pins.get(1));
                       editor.putString("h2lat", h2lat = pins.get(2));
                       editor.putString("h2long", h2long = pins.get(3));
                       editor.putString("h3lat", h3lat = pins.get(4));
                       editor.putString("h3long", h3long = pins.get(5));
                       editor.putString("h4lat", h4lat = pins.get(6));
                       editor.putString("h4long", h4long = pins.get(7));
                       editor.putString("h5lat", h5lat = pins.get(8));
                       editor.putString("h5long", h5long = pins.get(9));
                       editor.putString("h6lat", h6lat = pins.get(10));
                       editor.putString("h6long", h6long = pins.get(11));
                       editor.putString("h7lat", h7lat = pins.get(12));
                       editor.putString("h7long", h7long = pins.get(13));
                       editor.putString("h8lat", h8lat = pins.get(14));
                       editor.putString("h8long", h8long = pins.get(15));
                       editor.putString("h9lat", h9lat = pins.get(16));
                       editor.putString("h9long", h9long = pins.get(17));
                       editor.putString("h10lat", h10lat = pins.get(18));
                       editor.putString("h10long", h10long = pins.get(19));
                       editor.putString("h11lat", h11lat = pins.get(20));
                       editor.putString("h11long", h11long = pins.get(21));
                       editor.putString("h12lat", h12lat = pins.get(22));
                       editor.putString("h12long", h12long = pins.get(23));
                       editor.putString("h13lat", h13lat = pins.get(24));
                       editor.putString("h13long", h13long = pins.get(25));
                       editor.putString("h14lat", h14lat = pins.get(26));
                       editor.putString("h14long", h14long = pins.get(27));
                       editor.putString("h15lat", h15lat = pins.get(28));
                       editor.putString("h15long", h15long = pins.get(29));
                       editor.putString("h16lat", h16lat = pins.get(30));
                       editor.putString("h16long", h16long = pins.get(31));
                       editor.putString("h17lat", h17lat = pins.get(32));
                       editor.putString("h17long", h17long = pins.get(33));
                       editor.putString("h18lat", h18lat = pins.get(34));
                       editor.putString("h18long", h18long = pins.get(35));
                       editor.apply();
                   }//if/else
               }//onItemClick
           });
       }
   }

}//class
