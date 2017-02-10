package com.redballgolf.golfSG.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.redballgolf.golfSG.Common.BaseActivity;
import com.redballgolf.golfSG.R;
import com.redballgolf.golfSG.RoundOfGolf.NewRound;
import com.redballgolf.golfSG.SharedPreferences.Preferences;

public class AfterLogin extends BaseActivity {

    public static String LoggedInId;
    private String NoOneLoggedIn = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);

        LoggedInId = Preferences.getPreferences(this, "loginID");
        if(LoggedInId.equals(NoOneLoggedIn)) {
            disableLeaguesAndLogoutButtons();
        }
    }//onCreate

    private void disableLeaguesAndLogoutButtons(){
        Button myleagues = (Button) findViewById(R.id.gotToLeagues);
        myleagues.setEnabled(false);
        //myleagues.setBackgroundResource(R.drawable.blank_button_gray);
        //Button myRounds = (Button)findViewById(R.id.gotToListOfRounds);
        //myRounds.setEnabled(false);
        //myRounds.setBackgroundResource(R.drawable.blank_button_gray);
        Button mylogout = (Button) findViewById(R.id.logout);
        mylogout.setVisibility(View.GONE);
    }

    public void goToListOfRounds(View view){
        Log.i("TAG", "gotToListOfRounds started");
//        Intent intentGoToListOfRounds = new Intent(AfterLogin.this,ListOfRounds.class);
//        startActivity(intentGoToListOfRounds);
    }

    public void goToNewRound(View view){
        Log.i("TAG", "goToNewRound started");
        Intent intentGoToNewRound = new Intent(AfterLogin.this, NewRound.class);
        startActivity(intentGoToNewRound);
    }

    public void goToLeagues(View view){
        Log.i("TAG", "goToNewRound started");
//        Intent intentGoToLeagues = new Intent(AfterLogin.this,ListOfLeagues.class);
//        startActivity(intentGoToLeagues);
    }

    public void Logout(View view){
        Preferences.removeFromPreferences(this, "loginID");
        LoginScript.loginID = NoOneLoggedIn;

        Intent intentGoToMain = new Intent(AfterLogin.this,Login.class);
        startActivity(intentGoToMain);
    }

    /*
    @Override
    public void onBackPressed() {
    }
    */
}//class
