package com.redballgolf.golfSG.RoundOfGolf;


import com.redballgolf.golfSG.GPS.Coordinates;

public class Flag {
    private double flagLatitude;
    private double flagLongitude;
    private int holeNumber;

    public Flag() {
        this.flagLatitude = Coordinates.getLatitude();
        this.flagLongitude = Coordinates.getLongitude();
    }

    //constructor for testing
    public Flag(double lat, double lng) {
        this.flagLatitude = lat;
        this.flagLongitude = lng;
    }

    public double getFlagLatitude() {
        return flagLatitude;
    }

    public double getFlagLongitude() {
        return flagLongitude;
    }

    public void setHoleNumber(int holeNumber) {
        this.holeNumber = holeNumber;
    }

    public int getHoleNumber() {
        return holeNumber;
    }
}
