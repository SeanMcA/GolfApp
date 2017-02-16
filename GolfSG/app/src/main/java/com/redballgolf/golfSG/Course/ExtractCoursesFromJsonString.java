package com.redballgolf.golfSG.Course;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;



public class ExtractCoursesFromJsonString {

    public static ArrayList<HashMap<String, String>> CourseListForListView;
    private static final String TAG_COURSE_INFO = "course_info";
    private static final String TAG_FACILITY_NAME = "facility_name";
    private static final String TAG_COURSE_NAME = "name";
    private static final String TAG_COURSE_ID = "course_id";
    private static final String TAG_COURSE_PINS = "pins";

    private static String[] courseIDsArray;
    private static String[] courseNamesArray;
    private static double[] coursePinsArray;

    public static ArrayList<HashMap<String, String>> getCoursesFromJsonString(String json) {
        if (json != null) {
            try {
                extractData(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.i("TAG", " Couldn't get any data from the url");
        }
        //Log.i("TAG", "Extract - start adapter");
        return CourseListForListView;
    }


    private static ArrayList extractData(String json) throws JSONException {
        //ArrayList of HashMaps for Courses listView. Each Hashmap is a row in the Courses listview.
        //The Hashmap contains the key - value pairs of:
        //course name - course name
        //courseID - courseID
        //coursePins - coursePins
        CourseListForListView = new ArrayList<HashMap<String, String>>();
        JSONObject jsonObj = new JSONObject(json);

        // Getting JSON data where the start tag is 'TAG_COURSE_INFO'
        JSONArray courses = jsonObj.getJSONArray(TAG_COURSE_INFO);

        //initialise arrays.
        //Data is put into arrays as well as the ArrayList 'CourseListForListView'.
        //The CourseListForListView is to display data in the ListView on screen
        //but the arrays are used when a user selects one of the items in the ListView.
        courseIDsArray = new String[courses.length()];
        courseNamesArray = new String[courses.length()];
        coursePinsArray = new double[36];

        // looping through All courses and get required data.
        for (int i = 0; i < courses.length(); i++) {
            JSONObject jsonObjectOfCourses = courses.getJSONObject(i);
            String courseFacilityName = jsonObjectOfCourses.getString(TAG_FACILITY_NAME);
            String courseName = jsonObjectOfCourses.getString(TAG_COURSE_NAME);
            String courseID = jsonObjectOfCourses.getString(TAG_COURSE_ID);
            String coursePin = jsonObjectOfCourses.getString(TAG_COURSE_PINS);

            //Hashmap to hold data for single course.
            HashMap<String, String> course = new HashMap<>();

            // Add the data for each course to the  'course' HashMap. course.put(key, value)
            course.put(TAG_FACILITY_NAME, courseFacilityName);
            course.put(TAG_COURSE_NAME, courseName);
            course.put(TAG_COURSE_ID, courseID);

            // Put the same data into the arrays.
            courseIDsArray[i] = courseID;
            courseNamesArray[i] = courseName;
            coursePinsArray[i] = Double.valueOf(coursePin);
            // Add the 'course' HashMap to the 'CourseListForListView' ArrayList.
            CourseListForListView.add(course);
        }
        return CourseListForListView;
    }

    public static double[] getCoursePinsArray() {
        return coursePinsArray;
    }

    public static String[] getCourseIDsArray() {
        return courseIDsArray;
    }

    public static String[] getCourseNamesArray() {
        return courseNamesArray;
    }
}//class
