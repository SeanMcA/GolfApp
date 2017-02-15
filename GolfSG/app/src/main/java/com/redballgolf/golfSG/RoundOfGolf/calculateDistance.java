package com.redballgolf.golfSG.RoundOfGolf;



public class CalculateDistance {
    public static double distanceIs(Double Lat, Double Long, Double lastLat, Double lastLong){
        double theta = Long - lastLong;
        double dist = Math.sin(deg2rad(Lat)) * Math.sin(deg2rad(lastLat)) + Math.cos(deg2rad(Lat)) * Math.cos(deg2rad(lastLat)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        dist = dist * 1000;
        //Log.i(TAG, "The Distance for row " + thisRow + " is:");
        //Log.i(TAG, "M1 - DISTANCE BETWEEN IS:  : " + dist + " meters.");
        dist = dist * 1.09361;
        //Log.i(TAG, "DISTANCE TO GREEN IS:  : " + dist + " yards.");

        return dist;
    }


    /**
     * This function converts decimal degrees to radians.
     * @param deg The degrees to be converted into Radians.
     * @return Result in Radians
     */
    private static double deg2rad(double deg)
    {
        return (deg * Math.PI / 180.0);
    }



    /**
     * This function converts radians to decimal degrees.
     * @param rad The Radians to be converted into degrees.
     * @return Results in Degress
     */
    private static double rad2deg(double rad)
    {
        return (rad * 180 / Math.PI);
    }

}
