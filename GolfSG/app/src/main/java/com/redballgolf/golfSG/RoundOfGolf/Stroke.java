package com.redballgolf.golfSG.RoundOfGolf;


import android.os.Parcel;
import android.os.Parcelable;

public class Stroke extends Shot{
    //private static int strokeNumber = 1;

    public Stroke(String lie){
        super(lie);
    }

    //constructor for testing
    public Stroke(double lat, double lng, String lie){
        super(lat, lng, lie);
    }




    //PARCELABLE CODE
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    protected Stroke(Parcel in) {
        super(in);
    }

    public static final Creator<Stroke> CREATOR = new Creator<Stroke>() {
        @Override
        public Stroke createFromParcel(Parcel source) {
            return new Stroke(source);
        }

        @Override
        public Stroke[] newArray(int size) {
            return new Stroke[size];
        }
    };
}