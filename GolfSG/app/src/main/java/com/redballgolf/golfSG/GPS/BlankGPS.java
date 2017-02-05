package com.redballgolf.golfSG.GPS;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.sitting_room.golfsg.R;

public class BlankGPS  {
    public startGPS sGPS = new startGPS();
    TextView myLocationText;
    Context mContext;
    public static LocationManager mLocationManager;
    public static LocationListener mLocationListener;
    public static long POLLING_FREQUENCY = 0;//milliseconds.
    private static float MIN_DISTANCE = 1;//meters
    public static double latitude;
    public static double longitude;


    public BlankGPS(Context mContext, TextView myLocationText)
    {
        this.mContext = mContext;
        this.myLocationText = myLocationText;
    }


    protected void Create() {
        Log.i("TAG", "BlankGPS Create started");

        // Define a listener that responds to location updates
        mLocationListener = new LocationListener() {

            public void onLocationChanged(Location location) {
                Log.i("TAG", "BlankGPS onLocationChanged");
                latitude = location.getLatitude();
                Log.i("TAG", "Latitude is");
                // Called when a new location is found by the network location provider.
                makeUseOfNewLocation(location);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };//LocationListener

        if (ContextCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            Log.i("TAG", "BlankGPS inside the ContxtCompat");
            mLocationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0,0, mLocationListener);
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,0, mLocationListener);

        }


    }//onCreate

    /**
     * Upon change of location the coordinates are send to this method,
     * where the latitude and longitude variables are updated     *
     * @param loc The location coordinates.
     */
    private void makeUseOfNewLocation(Location loc) {
        Log.i("TAG", "makeUseOfNewLocation started");
        latitude = loc.getLatitude();
        longitude = loc.getLongitude();
        Log.i("TAG", "BlankGPS- makeUseOfNewLocation-lat is: " + latitude);
        myLocationText.setText("Latitude is: " + latitude + "\n Longitude is: " + longitude);

    }




}
