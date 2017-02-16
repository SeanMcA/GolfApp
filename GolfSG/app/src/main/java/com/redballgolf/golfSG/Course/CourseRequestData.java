package com.redballgolf.golfSG.Course;

import com.redballgolf.golfSG.GPS.Coordinates;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by sitting-room on 12/02/2017.
 */

public class CourseRequestData {

    public static String courseData(String... args) throws UnsupportedEncodingException {
        String username = args[0];
        String password = args[1];
        String email = args[2];

        String data = URLEncoder.encode("sentUsername", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
        data+="&"+URLEncoder.encode("sentPassword","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
        data+="&"+URLEncoder.encode("sentEmail","UTF-8")+"="+URLEncoder.encode(email,"UTF-8");
        return data;
    }

    public static String courseUrl(){
        return "http://zelusit.com/androidGetCourses.php?latitude=" + Coordinates.getLatitude() + "&longitude=" + Coordinates.getLongitude();
        //http://zelusit.com/androidGetCourses.php?latitude=53.3498&longitude=-6.2603
    }
}
