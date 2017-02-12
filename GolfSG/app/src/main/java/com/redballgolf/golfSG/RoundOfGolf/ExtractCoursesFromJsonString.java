package com.redballgolf.golfSG.RoundOfGolf;

import android.content.Context;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import com.redballgolf.golfSG.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;



public class ExtractCoursesFromJsonString {

    public static ArrayList<HashMap<String, String>> CourseList;
    private static final String TAG_COURSE_INFO = "course_info";
    private static final String TAG_FACILITY_NAME = "facility_name";
    private static final String TAG_COURSE_NAME = "name";
    private static final String TAG_COURSE_ID = "course_id";
    private static final String TAG_COURSE_PINS = "pins";

    private static String[] courseIDsArray;
    private static String[] courseNamesArray;
    private static String[] coursePinsArray;

    private static String courseID;
    private static String coursePin;

    public static ArrayList<HashMap<String, String>> getCourses(String json) {
        if (json != null) {
            try {
                // Hashmap for ListView
                CourseList = new ArrayList<>();

                JSONObject jsonObj = new JSONObject(json);

                // Getting JSON Array node
                JSONArray courses = jsonObj.getJSONArray(TAG_COURSE_INFO);

                //initialise arrays
                courseIDsArray = new String[courses.length()];
                courseNamesArray = new String[courses.length()];
                coursePinsArray = new String[36];

                // looping through All courses
                for (int i = 0; i < courses.length(); i++) {
                    //Log.i(TAG, "courses length is: " + courses.length());
                    JSONObject c = courses.getJSONObject(i);

                    String courseFacilityName = c.getString(TAG_FACILITY_NAME);
                    String courseName = c.getString(TAG_COURSE_NAME);
                    courseID = c.getString(TAG_COURSE_ID);
                    coursePin = c.getString(TAG_COURSE_PINS);

                    // tmp hashmap for single course
                    HashMap<String, String> course = new HashMap<>();

                    // adding each child node to HashMap key => value
                    course.put(TAG_FACILITY_NAME, courseFacilityName);
                    course.put(TAG_COURSE_NAME, courseName);
                    course.put(TAG_COURSE_ID,courseID);


                    courseIDsArray[i] = courseID;
                    courseNamesArray[i] = courseName;
                    coursePinsArray[i] = coursePin;
                    // adding course to courses list
                    CourseList.add(course);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.i("TAG", " Couldn't get any data from the url");
        }
        Log.i("TAG", "Extract - start adapter");
        return CourseList;
    }//ParsonJson

}
