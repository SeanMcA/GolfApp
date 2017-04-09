package com.redballgolf.golfSG.RoundOfGolf;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
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
            Shot stroke = (Shot)listIterator.next();
            String lie = stroke.getLie();
            Log.i("TAG","Hole(getSummaryData) - lie is: " + lie);
            String score = String.valueOf(stroke.getShotScore());
            holeSummary.add(lie);
            holeSummary.add(score);
        }
        return holeSummary;
    }


    public void addShotToList(Shot stroke) {
        shotList.add(stroke);
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





    protected Hole(Parcel in) {
        this.shotList = new LinkedList<>();
        //in.readList(shotList, List.class.getClassLoader());
        in.readList(shotList, Hole.class.getClassLoader());

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
        dest.writeList(shotList);
    }


    //PARCELABLE CODE
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeTypedList(shotList);
//    }
//
//    protected Hole(Parcel in) {
//        this.shotList = new LinkedList<>();
//        in.readList(shotList, List.class.getClassLoader() );
//    }
//
//    public static final Creator<Hole> CREATOR = new Creator<Hole>() {
//        @Override
//        public Hole createFromParcel(Parcel in) {
//            return new Hole(in);
//        }
//
//        @Override
//        public Hole[] newArray(int size) {
//            return new Hole[size];
//        }
//    };

}
