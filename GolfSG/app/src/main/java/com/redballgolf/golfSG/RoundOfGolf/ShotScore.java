package com.redballgolf.golfSG.RoundOfGolf;


import java.util.Iterator;
import java.util.ListIterator;

public class ShotScore {
    private static double shotDifficulty;
    private static int strokeGainedArrayIndex;

    public static void calculateEachShotOnThis(Hole hole, Flag flag){
        double flagLatitude = flag.getFlagLatitude();
        double flagLongitude = flag.getFlagLongitude();
        Iterator listIterator = hole.getShotList().listIterator();
        while (listIterator.hasNext()) {
            double shotLatitude = ((Shot)listIterator.next()).getShotLatitude();
            double shotLongitude = ((Shot)listIterator.next()).getShotLongitude();
            String lie = ((Shot)listIterator.next()).getLie();

            double shotDistance = CalculateDistance.distanceIs(shotLatitude, shotLongitude, flagLatitude, flagLongitude);
            ((Shot)listIterator.next()).setDistanceOfShot(shotDistance);

            shotDifficulty = getShotDifficulty(shotDistance, lie);
            ((Shot)listIterator.next()).setShotDifficultyRating(shotDifficulty);
        }
    }

    public static void calculateFinalShotScore(Hole hole){
        ListIterator listIterator = hole.getShotList().listIterator();
        while (listIterator.hasPrevious()) {
            double score1 = ((Shot)listIterator.next()).getShotDifficultyRating();
            double score2 = ((Shot)listIterator.previous()).getShotDifficultyRating();
            double score = score2 - score1;
        }
    }

    private static double getShotDifficulty(double shotDistance, String lie){
        int distance = roundOfDistance(shotDistance);
        if(lie.equals(ShotInputScreen.PUTT)){
            strokeGainedArrayIndex = getPuttingIndex(distance);
        }else{
            strokeGainedArrayIndex = getArrayIndex(distance);
        }

        if(lie.equals(ShotInputScreen.TEESHOTDRIVER)){
            shotDifficulty = StrokesGainedArrays.getTeeDifficulty(strokeGainedArrayIndex);
        }else if(lie.equals(ShotInputScreen.FAIRWAY)){
            shotDifficulty = StrokesGainedArrays.getFairwayDifficulty(strokeGainedArrayIndex);
        }else if(lie.equals(ShotInputScreen.ROUGH)){
            shotDifficulty = StrokesGainedArrays.getRoughDifficulty(strokeGainedArrayIndex);
        }else if(lie.equals(ShotInputScreen.SAND)){
            shotDifficulty = StrokesGainedArrays.getSandDifficulty(strokeGainedArrayIndex);
        }else if(lie.equals(ShotInputScreen.RECOVERY)){
            shotDifficulty = StrokesGainedArrays.getRecoveryDifficulty(strokeGainedArrayIndex);
        }else if(lie.equals(ShotInputScreen.PUTT)){
            shotDifficulty = StrokesGainedArrays.getPuttDifficulty(strokeGainedArrayIndex);
        }else if(lie.equals(ShotInputScreen.PENALTY)){
            shotDifficulty = 0;
        }else{shotDifficulty = 200;}
        return shotDifficulty;
    }

    private static int roundOfDistance(double distance){
        double roundedDistanceDouble = (distance + 20) - (distance % 20);
        int roundedDistance = (int) roundedDistanceDouble;
        return roundedDistance;
    }

    private static int getArrayIndex(int roundedDistance){
        int strokesGainedArrayIndex = (roundedDistance / 20) - 1;
        return strokesGainedArrayIndex;
    }

    private static int getPuttingIndex(int roundedDistance){
        int arrayIndex;
        if(roundedDistance <= 3){
            arrayIndex = 0;
        }
        else if(roundedDistance <= 4){
            arrayIndex = 1;
        }
        else if(roundedDistance <= 5){
            arrayIndex = 2;
        }
        else if(roundedDistance <= 6){
            arrayIndex = 3;
        }
        else if(roundedDistance <= 7){
            arrayIndex = 4;
        }
        else if(roundedDistance <= 8){
            arrayIndex = 5;
        }
        else if(roundedDistance <= 9){
            arrayIndex = 6;
        }
        else if(roundedDistance <= 10){
            arrayIndex = 7;
        }
        else if(roundedDistance <= 15){
            arrayIndex = 8;
        }
        else if(roundedDistance <= 20){
            arrayIndex = 9;
        }
        else if(roundedDistance <= 30){
            arrayIndex = 10;
        }
        else if(roundedDistance <= 40){
            arrayIndex = 11;
        }
        else if(roundedDistance <= 50){
            arrayIndex = 12;
        }
        else if(roundedDistance <= 60){
            arrayIndex = 13;
        }
        else{
            arrayIndex = 14;
        }
        return arrayIndex;
    }
}
