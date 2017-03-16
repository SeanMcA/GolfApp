package com.redballgolf.golfSG.RoundOfGolf;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Hole implements Parcelable{
    private List<Shot> shotList;
    private static int holeNumber = 1;

    public Hole() {
        shotList = new ArrayList<>();
    }

    public ArrayList getHoleSummary() {
        ArrayList holeSummary = new ArrayList();
        Iterator listIterator = shotList.listIterator();
        while (listIterator.hasNext()) {
            Shot shot = ((Shot)listIterator.next());
            String lie = shot.getLie();
            Log.i("TAG","Hole - lie is: " + lie);
            String score = String.valueOf(shot.getShotScore());
            holeSummary.add(lie);
            holeSummary.add(score);
        }
        return holeSummary;
    }


    public void addShotToList(Shot shot) {
        shotList.add(shot);
    }

    public List<Shot> getShotList() {
        return shotList;
    }

    public static int getHoleNumber() {
        return holeNumber;
    }

    public static void incrementHoleNumber() {
        Hole.holeNumber++;
    }

    //PARCELABLE CODE.
    protected Hole(Parcel in) {

        shotList = in.createTypedArrayList(Shot.CREATOR);
    }

    public static final Creator<Hole> CREATOR = new Creator<Hole>() {
        @Override
        public Hole createFromParcel(Parcel in) {
            return new Hole(in);
        }

        @Override
        public Hole[] newArray(int size) {
            return new Hole[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(shotList);
    }
}
