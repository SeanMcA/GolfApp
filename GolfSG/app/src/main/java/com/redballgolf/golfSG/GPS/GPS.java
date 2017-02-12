package com.redballgolf.golfSG.GPS;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;


public class GPS {
    private Context mContext;

    public GPS(Context mContext) {
        this.mContext = mContext;
        getLocation();
    }

    private void getLocation() {
        Log.i("TAG", "GPS getLocation started");


        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        final LocationListener locationListener = new LocationListener() {

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

        LocationManager lm = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    }//onCreate

    private void makeUseOfNewLocation(Location loc) {
        Log.i("TAG", "makeUseOfNewLocation started");
        Log.i("TAG", "Latitude is: " + loc.getLatitude());
        Log.i("TAG", "Longitude is: " + loc.getLongitude());
        Log.i("TAG", "Accuracy is:" + loc.getAccuracy());
        Coordinates.setLatitude(loc.getLatitude());
        Coordinates.setLongitude(loc.getLongitude());
        Coordinates.setAccuracy(loc.getAccuracy());
    }
}//class
