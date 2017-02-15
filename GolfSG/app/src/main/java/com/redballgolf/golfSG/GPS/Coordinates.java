package com.redballgolf.golfSG.GPS;


import com.redballgolf.golfSG.ObserverSubject.Observer;
import com.redballgolf.golfSG.ObserverSubject.Subject;

import java.util.ArrayList;

public class Coordinates implements Subject{
    private static double currentLatitude;
    private static double currentLongitude;
    private static double accuracy;
    private ArrayList observers;

    public Coordinates(){
        observers = new ArrayList();
    }



    public static double getLatitude() {
        return currentLatitude;
    }

    protected static void setLatitude(Double latitude) {
        Coordinates.currentLatitude = latitude;
    }

    public static double getLongitude() {
        return currentLongitude;
    }

    protected static void setLongitude(Double longitude) {
        Coordinates.currentLongitude = longitude;
    }

    public static double getAccuracy() {
        return Coordinates.accuracy;
    }

    protected static void setAccuracy(double accuracy) {
        Coordinates.accuracy = accuracy;
    }


    @Override
    public void registerObserver(Observer o){
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if(i >= 0){
            observers.remove(i);
        }
    }

    @Override
    public void notifyObservers() {
        for(int i = 0; i < observers.size(); i++){
            Observer observer = (Observer) observers.get(i);
            observer.update(currentLatitude, currentLongitude, accuracy);
        }
    }
}//Coordiantes
