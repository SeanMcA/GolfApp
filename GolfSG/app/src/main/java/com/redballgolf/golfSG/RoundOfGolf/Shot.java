package com.redballgolf.golfSG.RoundOfGolf;


import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.redballgolf.golfSG.GPS.Coordinates;
import com.redballgolf.golfSG.SQLite.DatabaseHelper;

public abstract class Shot implements Parcelable{
    private double shotLatitude;
    private double shotLongitude;
    private String lie;
    private double distanceOfShot;
    private double shotDifficultyRating = 0.0;
    private double shotScore;
    private static int shotNumber = 1;
    private static int holeNumber;

    public Shot(String lie){
        this.lie = lie;
        this.shotLatitude = Coordinates.getLatitude();
        this.shotLongitude = Coordinates.getLongitude();
        this.holeNumber = Hole.getHoleNumber();
    }

    public Shot(Parcel in) {
    }

    //Constructor for testing
    public Shot(double lat, double lng, String lie){
        this.lie = lie;
        this.shotLatitude = lat;
        this.shotLongitude = lng;
    }

    public void addShotToSqlite(Context context){
        DatabaseHelper dbHandler = new DatabaseHelper(context);
        dbHandler.addShotToDB(shotLatitude, shotLongitude, lie, Hole.getHoleNumber(), getShotNumber(), Round.getRoundID());
        shotNumber++;
    }


    public double getShotLatitude() {
        return shotLatitude;
    }

    public double getShotLongitude() {
        return shotLongitude;
    }

    public String getLie() {
        return lie;
    }

    public double getDistanceOfShot() {
        return distanceOfShot;
    }

    public double getShotDifficultyRating() {
        return shotDifficultyRating;
    }

    public double getShotScore() {
        return shotScore;
    }


    public void setDistanceOfShot(double distanceOfShot) {
        this.distanceOfShot = distanceOfShot;
    }

    public void setShotDifficultyRating(double shotDifficultyRating) {
        this.shotDifficultyRating = shotDifficultyRating;
    }

    public void setShotScore(double shotScore) {
        this.shotScore = shotScore;
    }

    public static int getShotNumber() {
        return shotNumber;
    }

    public static void resetShotNumber(){
        Shot.shotNumber = 1;
    }

}
