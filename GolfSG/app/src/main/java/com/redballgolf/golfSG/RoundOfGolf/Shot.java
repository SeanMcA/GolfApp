package com.redballgolf.golfSG.RoundOfGolf;


import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.redballgolf.golfSG.GPS.Coordinates;
import com.redballgolf.golfSG.SQLite.DatabaseHelper;

public class Shot implements Parcelable{
    private double shotLatitude;
    private double shotLongitude;
    private String lie;
    private double distanceOfShot;
    private double shotDifficultyRating;
    private double shotScore;
    private static int shotNumber = 1;

    public Shot(String lie){
        this.lie = lie;
        this.shotLatitude = Coordinates.getLatitude();
        this.shotLongitude = Coordinates.getLongitude();
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



    //PARCELABLE CODE.
    protected Shot(Parcel in) {
        shotLatitude = in.readDouble();
        shotLongitude = in.readDouble();
        lie = in.readString();
        distanceOfShot = in.readDouble();
        shotDifficultyRating = in.readDouble();
        shotScore = in.readDouble();
    }

    public static final Creator<Shot> CREATOR = new Creator<Shot>() {
        @Override
        public Shot createFromParcel(Parcel in) {
            return new Shot(in);
        }

        @Override
        public Shot[] newArray(int size) {
            return new Shot[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(shotLatitude);
        dest.writeDouble(shotLongitude);
        dest.writeString(lie);
        dest.writeDouble(distanceOfShot);
        dest.writeDouble(shotDifficultyRating);
        dest.writeDouble(shotScore);
    }
}
