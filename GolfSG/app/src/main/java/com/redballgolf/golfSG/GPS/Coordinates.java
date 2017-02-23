package com.redballgolf.golfSG.GPS;


import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.redballgolf.golfSG.ObserverSubject.Observer;
import com.redballgolf.golfSG.ObserverSubject.Subject;

import java.util.ArrayList;

public class Coordinates {
    private static double currentLatitude;
    private static double currentLongitude;
    private static double accuracy;
    private static ArrayList observers;

    public Coordinates(){
        observers = new ArrayList();
    }



    public void setCoordinates(double lat, double lng, double accuracy){
        //Log.i("TAG","Coordinates - setCoordinates started");
        this.currentLatitude = lat;
        this.currentLongitude = lng;
        this.accuracy = accuracy;
        notifyObservers();
    }

    public static double getLatitude() {
        return currentLatitude;
    }

    public static double getLongitude() {
        return currentLongitude;
    }

    public static double getAccuracy() {
        return accuracy;
    }



    public static void registerObserver(Observer o){
       //Log.i("TAG","Coordinates - observer registered.");
        observers.add(o);
        //Log.i("TAG","observers array size: " + observers.size());
        //Log.i("TAG", "observers contains: " + observers.get(0));
    }


    public static void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if(i >= 0){
            observers.remove(i);
        }
    }


    public static void notifyObservers() {
        //Log.i("TAG", "Coordinates - notifyObservers array size is: " + observers.size());
        for(int i = 0; i < observers.size(); i++){
            //Log.i("TAG", "Coordinates - inside 'for loop' of notifyObservers.");
            Observer observer = (Observer) observers.get(i);
            observer.update(currentLatitude, currentLongitude, accuracy);
        }
    }


}//Coordiantes
