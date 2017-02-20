package com.redballgolf.golfSG.Course;


import com.redballgolf.golfSG.Course.ExtractCoursesFromJsonString;
import com.redballgolf.golfSG.RoundOfGolf.Hole;
import com.redballgolf.golfSG.RoundOfGolf.ShotInputScreen;

public class PinsCoordinates {
    private static double pinLatitude;
    private static double pinLongitude;
    double[] pinsArray = ExtractCoursesFromJsonString.getCoursePinsArray();



    public void getCoordinates(){
        int hole_counter = Hole.getHoleNumber();
        this.pinLatitude = pinsArray[hole_counter * 2 -2];
        this.pinLongitude = pinsArray[hole_counter * 2 -1];
    }

    public static double getPinLatitude() {
        return pinLatitude;
    }

    public static double getPinLongitude() {
        return pinLongitude;
    }
}//class
