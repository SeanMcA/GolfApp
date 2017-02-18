package com.redballgolf.golfSG.RoundOfGolf;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Round {
    private int playerID;
    private List holeList = new LinkedList<>();
    private static int roundID;

    public Round(int roundID){
        this.roundID = roundID;
    }


    public void addHoleToRoundsHoleList(Hole hole){
       holeList.add(hole);
    }

    public List<Hole> getHoleList() {
        return holeList;
    }

    public static int getRoundID() {
        return roundID;
    }
}
