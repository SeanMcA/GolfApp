package com.redballgolf.golfSG.RoundOfGolf;


import android.os.Parcel;
import android.os.Parcelable;

import com.redballgolf.golfSG.GPS.Coordinates;

public class Putt extends Shot  implements Parcelable{
    private int numberOfPutts;
    private double firstPuttLatitude;
    private double firstPuttLongitude;


    public Putt(String lie){
        super(lie);
        this.firstPuttLatitude = Coordinates.getLatitude();
        this.firstPuttLongitude = Coordinates.getLongitude();
    }


    protected Putt(Parcel in) {
        super(in);
        numberOfPutts = in.readInt();
        firstPuttLatitude = in.readDouble();
        firstPuttLongitude = in.readDouble();
    }

    public static final Creator<Putt> CREATOR = new Creator<Putt>() {
        @Override
        public Putt createFromParcel(Parcel in) {
            return new Putt(in);
        }

        @Override
        public Putt[] newArray(int size) {
            return new Putt[size];
        }
    };

    public void setNumberOfPutts(int numberOfPutts) {
        this.numberOfPutts = numberOfPutts;
    }

    public int getNumberOfPutts() {
        return numberOfPutts;
    }


    public double getFirstPuttLatitude() {
        return firstPuttLatitude;
    }

    public double getFirstPuttLongitude() {
        return firstPuttLongitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(numberOfPutts);
        dest.writeDouble(firstPuttLatitude);
        dest.writeDouble(firstPuttLongitude);
    }
}
