package com.redballgolf.golfSG.GPS;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.sitting_room.golfsg.R;

public class startGPS extends Activity {
    TextView myLocationText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("TAG", "startGPS onCreate started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_gps);
        myLocationText = (TextView)findViewById(R.id.myLocationTV);

        GPS gps = new GPS(this);
        gps.getLocation();
    }


    public void getCoor(){
        double latitude = Coordinates.getLatitude();
        double longitude = Coordinates.getLongitude();
        double accuracy = Coordinates.getAccuracy();
    }

}//class
