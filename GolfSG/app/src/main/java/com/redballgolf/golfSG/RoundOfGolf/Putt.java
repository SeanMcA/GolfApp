package com.redballgolf.golfSG.RoundOfGolf;


import android.os.Parcel;
import android.os.Parcelable;


public class Putt extends Shot  implements Parcelable{
    private int numberOfPutts;

    public Putt(String lie){
        super(lie);
    }


    //constructor for testing
    public Putt(double lat, double lng, String lie, int numberOfPutts){
        super(lat, lng, lie);
        this.numberOfPutts = numberOfPutts;
    }


    protected Putt(Parcel in) {
        super(in);
        numberOfPutts = in.readInt();
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




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(numberOfPutts);
    }
}
