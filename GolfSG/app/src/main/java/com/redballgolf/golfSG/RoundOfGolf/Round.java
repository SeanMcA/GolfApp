package com.redballgolf.golfSG.RoundOfGolf;


import android.util.Log;

import java.util.LinkedList;
import java.util.List;

public class Round {
    private int playerID;
    private List<Hole> holeList = new LinkedList<>();
    private int roundID;

    public Round(int roundID){
        this.roundID = roundID;
        Hole hole = new Hole(roundID, this);
    }


    public void addHoleToRoundsHoleList(Hole hole){
       holeList.add(hole);
    }

    public List<Hole> getHoleList() {
        return holeList;
    }
}
