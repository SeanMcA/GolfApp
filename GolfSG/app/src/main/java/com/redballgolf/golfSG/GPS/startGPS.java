package com.redballgolf.golfSG.GPS;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.location.Location;

import com.example.sitting_room.golfsg.R;

public class startGPS extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("TAG", "startGPS onCreate started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_gps);
        TextView myLocationText = (TextView)findViewById(R.id.myLocationTV);

        BlankGPS bGps = new BlankGPS(this, myLocationText);
        bGps.Create();
    }

    public void displayCoor(double lat, double lng){
        Log.i("TAG", "displayCoor started");

        //Log.i("TAG", "displayCoor-TextView is: " + myLocationText);
        //myLocationText.setText("Your current position is:");
    }
}
