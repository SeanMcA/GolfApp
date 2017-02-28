package com.redballgolf.golfSG.Common;


import java.util.HashMap;
import java.util.Map;

public class StrokesGainedArrays {
    static Map<Integer, Double> teeShot = createTeeShotHashMap();
    static Map<Integer, Double> fairwayShot = createFairwayShotHashMap();




    private static Map<Integer, Double> createTeeShotHashMap(){
        Map<Integer, Double> teeShot = new HashMap<>();
        teeShot.put(20, 2.50);
        teeShot.put(40, 2.60);
        teeShot.put(60, 2.70);
        teeShot.put(80, 2.80);
        teeShot.put(100, 2.92);
        teeShot.put(120, 2.99);
        teeShot.put(140, 2.97);
        teeShot.put(160, 2.99);
        teeShot.put(180, 3.05);
        teeShot.put(200, 3.12);
        teeShot.put(220, 3.17);
        teeShot.put(240, 3.25);
        teeShot.put(260, 3.45);
        teeShot.put(280, 3.65);
        teeShot.put(300, 3.71);
        teeShot.put(320, 3.79);
        teeShot.put(340, 3.86);
        teeShot.put(360, 3.92);
        teeShot.put(380, 3.96);
        teeShot.put(400, 3.99);
        teeShot.put(420, 4.02);
        teeShot.put(440, 4.08);
        teeShot.put(460, 4.17);
        teeShot.put(480, 4.28);
        teeShot.put(500, 4.41);
        teeShot.put(520, 4.54);
        teeShot.put(540, 4.65);
        teeShot.put(560, 4.74);
        teeShot.put(580, 4.79);
        teeShot.put(600, 4.82);
        return teeShot;
    }

    private static Map<Integer, Double>createFairwayShotHashMap(){
        Map<Integer, Double> fairwayShot = new HashMap<>();
        fairwayShot.put(20, 2.40);

        return fairwayShot;
    }

}
