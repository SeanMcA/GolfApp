package com.redballgolf.golfSG.RoundOfGolf;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Round implements Parcelable{
    private int playerID;
    private List holeList;
    private static int roundID;

    public Round(int roundID){
        holeList = new LinkedList<>();
        this.roundID = roundID;
    }




    public void addHoleToRound(Hole hole){
       holeList.add(hole);
    }

    public List<Hole> getHoleList() {
        return holeList;
    }

    public static int getRoundID() {
        return roundID;
    }

    protected Round(Parcel in) {
        playerID = in.readInt();
        roundID = in.readInt();
        holeList = in.createTypedArrayList(Round.CREATOR);
    }


    //PARCELABLE CODE.
    public static final Creator<Round> CREATOR = new Creator<Round>() {
        @Override
        public Round createFromParcel(Parcel in) {
            return new Round(in);
        }

        @Override
        public Round[] newArray(int size) {
            return new Round[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(playerID);
        dest.writeInt(roundID);
        dest.writeTypedList(holeList);
    }
}
