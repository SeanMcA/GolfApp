package com.redballgolf.golfSG.GPS;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;


public class GPS {
    private Context mContext;

    public GPS(Context mContext)
    {
        this.mContext = mContext;
    }

    void getLocation() {
        Log.i("TAG", "GPS getLocation started");

        // Define a listener that responds to location updates
        LocationListener mLocationListener = new LocationListener() {

            public void onLocationChanged(Location location) {
                makeUseOfNewLocation(location);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

        if (ContextCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            LocationManager mLocationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0,0, mLocationListener);
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,0, mLocationListener);

        }
    }//getLocation

    private void makeUseOfNewLocation(Location loc) {
        Log.i("TAG", "makeUseOfNewLocation started");
        Coordinates.setLatitude(loc.getLatitude());
        Coordinates.setLongitude(loc.getLongitude());
        Coordinates.setAccuracy(loc.getAccuracy());
    }
}//class
