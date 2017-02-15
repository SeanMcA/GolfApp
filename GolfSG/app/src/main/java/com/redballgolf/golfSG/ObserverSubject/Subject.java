package com.redballgolf.golfSG.ObserverSubject;



public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
