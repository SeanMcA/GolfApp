package com.redballgolf.golfSG.RoundOfGolf;


import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

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



    //Constructor for testing
    public Shot(double lat, double lng, String lie){
        this.lie = lie;
        this.shotLatitude = lat;
        this.shotLongitude = lng;
    }

    public void addShotToSqlite(Context context){
        Log.i("TAG", "Logging shot to SQLite db");
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


    //PARCELABLE CODE
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.shotLatitude);
        dest.writeDouble(this.shotLongitude);
        dest.writeString(this.lie);
        dest.writeDouble(this.distanceOfShot);
        dest.writeDouble(this.shotDifficultyRating);
        dest.writeDouble(this.shotScore);
    }

    protected Shot(Parcel in) {
        this.shotLatitude = in.readDouble();
        this.shotLongitude = in.readDouble();
        this.lie = in.readString();
        this.distanceOfShot = in.readDouble();
        this.shotDifficultyRating = in.readDouble();
        this.shotScore = in.readDouble();
    }


}
