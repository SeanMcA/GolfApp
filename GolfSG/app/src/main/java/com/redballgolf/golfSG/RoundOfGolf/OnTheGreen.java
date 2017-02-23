package com.redballgolf.golfSG.RoundOfGolf;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.redballgolf.golfSG.Common.BaseActivity;
import com.redballgolf.golfSG.R;

public class OnTheGreen extends BaseActivity {
    Hole hole;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_the_green);

        Intent intent = getIntent();
        hole = intent.getExtras().getParcelable("Hole");

    }

}
