package com.redballgolf.golfSG.RoundOfGolf;


import android.os.Parcel;
import android.os.Parcelable;


public class Putt extends Shot{
    private int numberOfPutts;

    public Putt(String lie){
        super(lie);
    }


    //constructor for testing
    public Putt(double lat, double lng, String lie, int numberOfPutts){
        super(lat, lng, lie);
        this.numberOfPutts = numberOfPutts;
    }

    public void setNumberOfPutts(int numberOfPutts) {
        this.numberOfPutts = numberOfPutts;
    }

    public int getNumberOfPutts() {
        return numberOfPutts;
    }

   //PARCELABLE CODE

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.numberOfPutts);
    }

    protected Putt(Parcel in) {
        super(in);
        this.numberOfPutts = in.readInt();
    }

    public static final Creator<Putt> CREATOR = new Creator<Putt>() {
        @Override
        public Putt createFromParcel(Parcel source) {
            return new Putt(source);
        }

        @Override
        public Putt[] newArray(int size) {
            return new Putt[size];
        }
    };
}
