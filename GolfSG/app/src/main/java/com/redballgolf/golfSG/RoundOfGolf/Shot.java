package com.redballgolf.golfSG.RoundOfGolf;


import com.redballgolf.golfSG.GPS.Coordinates;

public class Shot {
    private double shotLatitude;
    private double shotLongitude;
    private String lie;
    private double distanceOfShot;
    private double shotDifficultyRating;
    private double shotScore;
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
}
