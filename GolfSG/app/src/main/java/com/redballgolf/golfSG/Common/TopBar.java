package com.redballgolf.golfSG.Common;


import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.redballgolf.golfSG.R;


public class TopBar extends AppCompatActivity {
    public Activity activity;

    public TopBar(Activity activity){
        this.activity = activity;
    }

    public void createTopBar(){
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        Log.i("TAG","TopBar - toolbar: " + toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        this.setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle(sentLeagueName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

//        public void create(Context context) {
//            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//            toolbar.setNavigationIcon(R.mipmap.ic_launcher);
//            setSupportActionBar(toolbar);
//            //getSupportActionBar().setTitle(sentLeagueName);
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setHomeButtonEnabled(true);
//    }

}



