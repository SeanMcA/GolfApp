package com.redballgolf.golfSG.RoundOfGolf;


import java.util.Iterator;

public class ShotScore {

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
            double shotDifficulty = shotDifficulty(shotDistance, lie);
        }
    }

    private static double shotDifficulty(double shotDistance, String lie){
        double shotDifficulty;
        if(lie.equals("firstPutt")){
            shotDifficulty = calculatePuttingDifficulty(shotDistance);
        }else{
            shotDifficulty = calculateShotDifficulty(shotDistance);
        }
        return shotDifficulty;
    }


    private static double calculatePuttingDifficulty(double distance){

    }

    private static double calculateShotDifficulty(double distance){

    }
}
