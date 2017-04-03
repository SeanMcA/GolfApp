package com.redballgolf.golfSG.RoundOfGolf;


public class ShotScore {
    private static double shotDifficulty;
    private static double firstPuttDifficulty;
    private static int strokeGainedArrayIndex;
    private static int roundedShotDistance;
    private static int roundedOffDistance;
    private static int roundedOffPuttingDistance;
    Hole hole;
    Flag flag;
    Putt putt;



    public static void calculateShotDistanceAndDifficulty(Hole hole, Flag flag, boolean isPutt){
        double flagLatitude = flag.getFlagLatitude();
        double flagLongitude = flag.getFlagLongitude();

        for(int i = 0; i < hole.getShotList().size(); i++) {
            Shot stroke = hole.getShotList().get(i);
            double shotLatitude = stroke.getShotLatitude();
            double shotLongitude = stroke.getShotLongitude();
            String lie = stroke.getLie();

            double shotDistance = CalculateDistance.distanceIs(shotLatitude, shotLongitude, flagLatitude, flagLongitude);
            roundedOffDistance = roundOffDistance(shotDistance);
            stroke.setDistanceOfShot(roundedOffDistance);
            shotDifficulty = getShotDifficulty(roundedOffDistance, lie);
            stroke.setShotDifficultyRating(shotDifficulty);
        }
        if(isPutt){
            int puttIndex = (hole.getShotList().size()) - 1;
            Putt putt = (Putt) hole.getShotList().get(puttIndex);
            double firstPuttLat = putt.getShotLatitude();
            double firstPuttLng = putt.getShotLongitude();
            double firstPuttDistance = CalculateDistance.distanceIs(firstPuttLat, firstPuttLng, flagLatitude, flagLongitude);
            double firstPuttDistanceInFeet = firstPuttDistance * 3.28084;
            roundedOffPuttingDistance = roundOffPuttingDistance(firstPuttDistanceInFeet);
            putt.setDistanceOfShot(roundedOffPuttingDistance);
            firstPuttDifficulty = getShotDifficulty(roundedOffPuttingDistance, ShotInputScreen.GREEN);
            putt.setShotDifficultyRating(firstPuttDifficulty);
        }

    }


    public static void calculateShotScore(Hole hole,  boolean isPutt){
        double score = 0;
        int size = hole.getShotList().size();
        if(isPutt){
            int puttIndex = (hole.getShotList().size()) - 1;
            Putt putt = (Putt) hole.getShotList().get(puttIndex);
            double puttDifficulty = putt.getShotDifficultyRating();
            int numberOfPutts = putt.getNumberOfPutts();
            double puttScore = puttDifficulty - numberOfPutts;
            putt.setShotScore(puttScore);
        }
        for(int i = 0; i < size - 1; i++){
            Shot shot = hole.getShotList().get(i);
            Shot nextStroke = hole.getShotList().get(i + 1);
            double nextShotDifficultyRating = nextStroke.getShotDifficultyRating();
            double thisShotDifficultyRating = shot.getShotDifficultyRating();
            double currentScore = shot.getShotScore();
            score = (thisShotDifficultyRating - nextShotDifficultyRating) - 1;
            shot.setShotScore(currentScore + score);
        }
        if(!isPutt){
            Shot shot = hole.getShotList().get(size - 1);
            score = (shot.getShotDifficultyRating()) - 1;
            shot.setShotScore(score);
        }
    }



    private static double getShotDifficulty(int shotDistance, String lie){
        if(lie.equals(ShotInputScreen.GREEN)){
            strokeGainedArrayIndex = getPuttingIndex(shotDistance);
        }else {
            strokeGainedArrayIndex = getArrayIndex(shotDistance);
        }
//

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
        }else if(lie.equals(ShotInputScreen.GREEN)){
            shotDifficulty = StrokesGainedArrays.getPuttDifficulty(strokeGainedArrayIndex);
        }else if(lie.equals(ShotInputScreen.PENALTY)){
            shotDifficulty = 0;
        }else{shotDifficulty = 0;}
        return shotDifficulty;
    }

    private static int roundOffPuttingDistance(double distance){
        int roundedDistance;
        if(distance <= 3){
            roundedDistance = 3;
        }else if(distance <= 4){
            roundedDistance = 4;
        }else if(distance <= 5){
            roundedDistance = 5;
        }else if(distance <= 6){
            roundedDistance = 6;
        }else if(distance <= 7){
            roundedDistance = 7;
        }else if(distance <= 8){
            roundedDistance = 8;
        }else if(distance <= 9){
            roundedDistance = 9;
        }else if(distance <= 10){
            roundedDistance = 10;
        }else if(distance <= 15){
            roundedDistance = 15;
        }else if(distance <= 20){
            roundedDistance = 20;
        }else if(distance <= 30){
            roundedDistance = 30;
        }else if(distance <= 40){
            roundedDistance = 40;
        }else if(distance <= 50){
            roundedDistance = 50;
        }else if(distance <= 60){
            roundedDistance = 60;
        }else if(distance <= 90){
            roundedDistance = 90;
        }else{
            roundedDistance = 100;
        }
        return roundedDistance;
    }

    private static int roundOffDistance(double distance){
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
