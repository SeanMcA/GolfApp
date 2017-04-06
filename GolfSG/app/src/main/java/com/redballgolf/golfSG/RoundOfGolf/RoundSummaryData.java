package com.redballgolf.golfSG.RoundOfGolf;



public class RoundSummaryData {
    private int roundID;
    private double strokesGainedPutting;
    private double strokesGainedLongGame;
    private double strokesGainedShortGame;
    private double strokesGainedDriving;
    private double strokesGainedScrambling;

    public RoundSummaryData(double strokesGainedPutting, double strokesGainedLongGame, double strokesGainedShortGame, double strokesGainedDriving, double strokesGainedScrambling) {
        this.strokesGainedPutting = strokesGainedPutting;
        this.strokesGainedLongGame = strokesGainedLongGame;
        this.strokesGainedShortGame = strokesGainedShortGame;
        this.strokesGainedDriving = strokesGainedDriving;
        this.strokesGainedScrambling = strokesGainedScrambling;
    }

    public double getStrokesGainedPutting() {
        return strokesGainedPutting;
    }

    public void setStrokesGainedPutting(double strokesGainedPutting) {
        this.strokesGainedPutting = strokesGainedPutting;
    }

    public double getStrokesGainedLongGame() {
        return strokesGainedLongGame;
    }

    public void setStrokesGainedLongGame(double strokesGainedLongGame) {
        this.strokesGainedLongGame = strokesGainedLongGame;
    }

    public double getStrokesGainedShortGame() {
        return strokesGainedShortGame;
    }

    public void setStrokesGainedShortGame(double strokesGainedShortGame) {
        this.strokesGainedShortGame = strokesGainedShortGame;
    }

    public double getStrokesGainedDriving() {
        return strokesGainedDriving;
    }

    public void setStrokesGainedDriving(double strokesGainedDriving) {
        this.strokesGainedDriving = strokesGainedDriving;
    }

    public double getStrokesGainedScrambling() {
        return strokesGainedScrambling;
    }

    public void setStrokesGainedScrambling(double strokesGainedScrambling) {
        this.strokesGainedScrambling = strokesGainedScrambling;
    }
}
