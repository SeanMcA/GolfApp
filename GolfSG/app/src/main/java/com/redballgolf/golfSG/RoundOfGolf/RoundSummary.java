package com.redballgolf.golfSG.RoundOfGolf;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringDef;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.redballgolf.golfSG.Common.BaseActivity;
import com.redballgolf.golfSG.R;

import java.util.ArrayList;
import java.util.List;

public class RoundSummary extends BaseActivity {
    TextView sgPuttingTextview;
    Round round;
    double sgDriving;
    double sgLongGame;
    double sgShortGame;
    double sgPutting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_summary);
        Intent intent = getIntent();
        round = intent.getExtras().getParcelable("Round");

        sgPuttingTextview = (TextView)findViewById(R.id.sgPuttingTextview);
        getRoundSummaryData(round);
        displayDataOnScreen();
    }

    private void getRoundSummaryData(Round round){
        List listOfHoles = round.getHoleList();
        int size = listOfHoles.size();
        for(int i = 0; i < size; i++) {//loop through holes 1 - 18
            List shotList = (List)listOfHoles.get(i);
            int shotListsize = shotList.size();
            for(int j = 0; j < shotListsize; j++){//loop through each shot on the hole
                Shot shot = (Shot)shotList.get(j);
                double distance = shot.getDistanceOfShot();
                double shotScore = shot.getShotScore();
                String lie = shot.getLie();
                if(lie.equals(ShotInputScreen.TEESHOTDRIVER)){
                    sgDriving = sgDriving + shotScore;
                }else if(lie.equals(ShotInputScreen.GREEN)){
                    sgPutting = sgPutting + shotScore;
                }else if(distance < 101){
                    sgShortGame = sgShortGame + shotScore;
                }else{
                    sgLongGame = sgLongGame + shotScore;
                }
            }
        }
    }

    private void displayDataOnScreen(){
        sgPuttingTextview.setText(Double.toString(sgPutting));
    }

}
