package com.redballgolf.golfSG.Common;


import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.redballgolf.golfSG.R;


public class TopBar extends AppCompatActivity {

        public void create(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

}



