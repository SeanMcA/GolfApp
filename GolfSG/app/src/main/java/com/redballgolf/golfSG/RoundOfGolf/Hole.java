package com.redballgolf.golfSG.RoundOfGolf;


import java.util.List;

public class Hole {
    private List<Shot> shotList;
    private int holeNumber;
    private int roundID;

    public Hole(int roundID, Round round) {
        this.roundID = roundID;
        this.holeNumber = ShotInputScreen.hole_counter;
        round.addHoleToRoundsHoleList(this);
    }

    public void newShot(){
        Shot shot = new Shot(this);
    }

    public void addShotToHolesShotList(Shot shot){
        shotList.add(shot);
    }

    public List<Shot> getShotList() {
        return shotList;
    }
}
