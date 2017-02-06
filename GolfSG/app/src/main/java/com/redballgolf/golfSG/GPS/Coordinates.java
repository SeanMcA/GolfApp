package com.redballgolf.golfSG.GPS;



public class Coordinates {
    private static double latitude;
    private static double longitude;
    private static double accuracy;


    public static double getLatitude() {
        return latitude;
    }

    protected static void setLatitude(Double latitude) {
        Coordinates.latitude = latitude;
    }

    public static double getLongitude() {
        return longitude;
    }

    protected static void setLongitude(Double longitude) {
        Coordinates.longitude = longitude;
    }

    public static double getAccuracy() { return accuracy; }

    protected static void setAccuracy(double accuracy) { Coordinates.accuracy = accuracy; }
}//Coordiantes
