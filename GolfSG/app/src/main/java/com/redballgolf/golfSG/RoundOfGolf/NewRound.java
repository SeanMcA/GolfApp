package com.redballgolf.golfSG.RoundOfGolf;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
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
import com.redballgolf.golfSG.Course.ExtractCoursesFromJsonString;
import com.redballgolf.golfSG.Course.GetListOfCourses;
import com.redballgolf.golfSG.GPS.Coordinates;
import com.redballgolf.golfSG.GPS.GPS;
import com.redballgolf.golfSG.R;
import com.redballgolf.golfSG.SQLite.DatabaseHelper;
import com.redballgolf.golfSG.SharedPreferences.Preferences;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewRound extends BaseActivity implements AdapterView.OnItemSelectedListener {
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
    private static String selectedCourseName;
    TextView selected_course;
    private Vibrator vibe;

    private static String LoggedInId;
    private static final String TAG_FACILITY_NAME = "facility_name";
    private static final String TAG_COURSE_NAME = "name";



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

        if(Coordinates.getAccuracy() <= 500){
            new RetrieveListOfCourses().execute();
        }
        addHandicapChoiceSpinner();
    }


    private class RetrieveListOfCourses extends AsyncTask<Void, Void, Void>{
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute(){
            Log.i("TAG", "GetListOfCourses started");
            pDialog = new ProgressDialog(context);
            pDialog.setMessage("Please wait while we retrieve courses in your location...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0){
            getCoursesListFromServer();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.i("TAG", "onPostExecute Json String: " + jsonData);
            displayCoursesInListView(pDialog);//
        }
    }//class


    private void getCoursesListFromServer(){
        String url = "http://zelusit.com/androidGetCourses.php?latitude="
                + Coordinates.getLatitude() + "&longitude=" + Coordinates.getLongitude();
        GetListOfCourses listOfCourses = new GetListOfCourses();
        jsonData = listOfCourses.retrieveCourses(url);
        mylistOfCourses = ExtractCoursesFromJsonString.getCoursesFromJsonString(jsonData);
    }

    private void displayCoursesInListView(ProgressDialog pDialog){
        listOfCoursesLV = (ListView) findViewById(R.id.listOfCourses);
        if (pDialog.isShowing()) {
            pDialog.dismiss();
        }

        if (mylistOfCourses != null) {

            ListAdapter adapter = new SimpleAdapter(
                    NewRound.this, mylistOfCourses,
                    R.layout.course_list_layout, new String[]{TAG_FACILITY_NAME, TAG_COURSE_NAME}, new int[]{R.id.facility_name, R.id.course_name}); //The new String above says which info goes in which TextView

            listOfCoursesLV.setAdapter(adapter);
            listOfCoursesLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    //Log.i(TAG, "Course ID: " + ExtractCoursesFromJsonString.getCourseIDsArray()[position]);
                    courseID = ExtractCoursesFromJsonString.getCourseIDsArray()[position];
                    selectedCourseName = ExtractCoursesFromJsonString.getCourseNamesArray()[position];
                    selected_course.setText("" + selectedCourseName);
                    vibe.vibrate(300);
                    Preferences.insert("courseID", courseID, context);
                }//onItemClick
            });
        }
        else
        {
            ToastMessage.displayLongToastMessage(context, "Could not find any Courses.");
        }
    }


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
            Log.i("TAG", "getRoundID - started");
            DatabaseHelper dbHandler = new DatabaseHelper(NewRound.this);
            roundID = dbHandler.getRoundID(today);
        }//try
        catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

            Preferences.insertInt("roundID", roundID, this);
            Preferences.insertBoolean("isNewRound", true, this);
            Intent intentGoToShotInputScreen = new Intent(NewRound.this, ShotInputScreen.class);
            intentGoToShotInputScreen.putExtra("roundID", roundID);
            //intentGoToShotInputScreen.putStringArrayListExtra("pinsArray", pins);
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


    @Override
    protected void onResume() {
        super.onResume();
        GPS.resumeListeners();
    }

    @Override
    protected void onPause() {
        super.onPause();
        GPS.removeListeners();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        handicap = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //put code here.
    }

}//class
