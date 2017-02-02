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

//        GetGPS myGPS = new GetGPS(this);
//        Location location = myGPS.getLocation();
//        String latLongString = myGPS.updateWithNewLocation(location);

        BlankGPS bGps = new BlankGPS(this);
        bGps.Create();
        Double latLongString = bGps.returnLat();


        TextView myLocationText = (TextView)findViewById(R.id.myLocationTV);
        myLocationText.setText("Your current position is:\n" + latLongString);
    }
}
