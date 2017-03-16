package com.redballgolf.golfSG.RoundOfGolf;



public class CalculateDistance {
    public static double distanceIs(double lat1, double lon1, double lat2, double lon2){
//        int Rk = 6373;
//        // convert coordinates to radians
//        lat1 = deg2rad(lat1);
//        lon1 = deg2rad(lon1);
//        lat2 = deg2rad(lat2);
//        lon2 = deg2rad(lon2);
//
//        // find the differences between the coordinates
//        double dlat = lat2 - lat1;
//        double dlon = lon2 - lon1;
//
//        // here's the heavy lifting
//        double a  = Math.pow(Math.sin(dlat/2),2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon/2),2);
//        double c  = 2 * Math.atan2(Math.sqrt(a),Math.sqrt(1-a)); // great circle distance in radians
//        double dk = c * Rk; // great circle distance in km
//        double distanceInMeters = dk * 1000;
//        double distanceInYards = distanceInMeters * 1.09361;
//        return distanceInYards;


        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        double distanceInKiometers = dist * 1.609344;
        double distanceInMeters = distanceInKiometers * 1000;
        double distanceInYards = distanceInMeters * 1.09361;
        return distanceInYards;
    }

    private static double deg2rad(double deg){
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad){
        return (rad * 180 / Math.PI);
    }

}
