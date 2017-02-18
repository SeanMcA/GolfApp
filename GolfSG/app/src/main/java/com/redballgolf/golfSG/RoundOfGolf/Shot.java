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
    private static int shotNumber = 1;
    private Hole hole;

    public Shot(Hole hole, String lie){
        this.hole = hole;
        this.lie = lie;
        this.shotLatitude = Coordinates.getLatitude();
        this.shotLongitude = Coordinates.getLongitude();
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

    public void addShotToSqlite(Context context){
        DatabaseHelper dbHandler = new DatabaseHelper(context);
        dbHandler.addShotToDB(shotLatitude, shotLongitude, lie, Hole.getHoleNumber(), getShotNumber(), Round.getRoundID());
        shotNumber++;
    }
}
