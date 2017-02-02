package com.redballgolf.golfSG.GPS;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.content.Context;



public class GetGPS {

    Context mContext;
    public static LocationManager mLocationManager;
    public static LocationListener mLocationListener;
    public static long POLLING_FREQUENCY = 0;//milliseconds.
    private static float MIN_DISTANCE = 1;//meters

    public GetGPS(Context mContext) {
        this.mContext = mContext;
    }

    public Location getLocation() {
        LocationManager locationManager;
        String context = Context.LOCATION_SERVICE;
        locationManager = (LocationManager) mContext.getSystemService(context);
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, POLLING_FREQUENCY, MIN_DISTANCE, mLocationListener);

        String provider = LocationManager.GPS_PROVIDER;
       Location location = locationManager.getLastKnownLocation(provider);
       return location;
    }

    public String updateWithNewLocation(Location location) {
        String latLongString;

        if (location != null){
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            latLongString = "Lat:" + lat + "\nLong:" + lng;
        }else{
            latLongString = "No Location";
        }

        return latLongString;
    }
}//GetGPS class