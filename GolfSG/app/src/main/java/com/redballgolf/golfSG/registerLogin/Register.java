package com.redballgolf.golfSG.registerLogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.sitting_room.golfsg.R;
import com.redballgolf.golfSG.GPS.startGPS;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);


    }

    public void goToGPS(View v){
        Log.i("TAG", "Reg. goToGPS started");
        Intent intentGoToGPS = new Intent(Register.this,startGPS.class);
        startActivity(intentGoToGPS);
    }

}
