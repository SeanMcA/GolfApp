package com.redballgolf.golfSG.Course;


import com.redballgolf.golfSG.Course.ExtractCoursesFromJsonString;
import com.redballgolf.golfSG.RoundOfGolf.ShotInputScreen;

public class PinsCoordinates {
    private static double pinLatitude;
    private static double pinLongitude;
    double[] pinsArray = ExtractCoursesFromJsonString.getCoursePinsArray();



    public void getCoordinates(){
        int hole_counter = ShotInputScreen.hole_counter;
        this.pinLatitude = pinsArray[hole_counter * hole_counter -2];
        this.pinLongitude = pinsArray[hole_counter * hole_counter -1];
    }

    public static double getPinLatitude() {
        return pinLatitude;
    }

    public static double getPinLongitude() {
        return pinLongitude;
    }
}//class
