package com.redballgolf.golfSG.ObserverSubject;



public interface Observer {
    void update(double currentLatitude, double currentLongitude, double accuracy);
}
