package com.redballgolf.golfSG.RoundOfGolf;


import java.util.List;

public class Round {
    private int playerID;
    private List<Hole> holeList;

    public void addHoleToRoundsHoleList(Hole hole){
       holeList.add(hole);
    }

    public List<Hole> getHoleList() {
        return holeList;
    }
}
