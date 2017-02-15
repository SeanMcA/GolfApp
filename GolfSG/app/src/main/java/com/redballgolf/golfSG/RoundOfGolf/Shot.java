package com.redballgolf.golfSG.RoundOfGolf;


import android.content.Context;

import com.redballgolf.golfSG.GPS.Coordinates;
import com.redballgolf.golfSG.SQLite.DatabaseHelper;

public class Shot {
    private double shotLatitude;
    private double shotLongitude;
    private String lie;
    private double distanceOfShot;
    private double shotDifficultyRating;
    private double shotScore;
    private static int shotNumber;
    private Hole hole;

    public Shot(Hole hole) {
        this.hole = hole;
    }

    public void addShotToHolesShotList(){
        hole.addShotToHolesShotList(this);
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

    public void setShotLatitude(double shotLatitude) {
        this.shotLatitude = shotLatitude;
    }

    public void setShotLongitude(double shotLongitude) {
        this.shotLongitude = shotLongitude;
    }

    public void setLie(String lie) {
        this.lie = lie;
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

    public void setShotCounter(int shotCounter) {
        this.shotNumber = shotCounter;
    }

    public void addShotToSqlite(Context context, String place, int roundID){
        DatabaseHelper dbHandler = new DatabaseHelper(context);
        dbHandler.addShotToDB(Coordinates.getLatitude(), Coordinates.getLongitude(), place, Hole.getHoleNumber(), getShotNumber(), roundID);
    }
}
