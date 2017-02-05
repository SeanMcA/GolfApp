package com.redballgolf.golfSG.GPS;

import android.util.Pair;

/**
 * Created by sitting-room on 05/02/2017.
 */

public class Coordinates {
    private Double latitude;
    private Double longitude;

    public Coordinates(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }


    /**
     * Convenience method for creating an appropriately typed pair.
     * @param latitude the first object in the Pair
     * @param longitude the second object in the pair
     * @return a Pair that is templatized with the types of a and b
     */
    public static <A, B> Pair <A, B> create(A latitude, B longitude) {
        return new Pair<A, B>(latitude, longitude);
    }
}//Coordiantes
