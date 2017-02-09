package com.redballgolf.golfSG.registerLogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.redballgolf.golfSG.R;
import com.redballgolf.golfSG.SharedPreferences.Preferences;

public class AfterLogin extends AppCompatActivity {

    private static final String TAG = "TEST";
    public static final String MyPREFERENCES = "MyPrefs";
    public static String LoggedInId;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle(sentLeagueName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        LoggedInId = Preferences.getPreferences(this, "loginID");
        Log.i("TAG", "AfterLogin scr - LoggedInId is: " + LoggedInId);
//        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        LoggedInId = sharedPreferences.getString("key", "0");

        Log.i(TAG, "1 ALG LoginID is: " + LoggedInId);

        //If user hasn't logged in then the 'leagues' button is disabled and 'grayed out'.
        if(LoggedInId.equals("0")) {
            Log.i(TAG, "2 ALG LoginID is: " + LoginScript.loginID);
            Button myleagues = (Button) findViewById(R.id.gotToLeagues);
            myleagues.setEnabled(false);
            //myleagues.setBackgroundResource(R.drawable.blank_button_gray);

            //Button myRounds = (Button)findViewById(R.id.gotToListOfRounds);
            //myRounds.setEnabled(false);
            //myRounds.setBackgroundResource(R.drawable.blank_button_gray);

            Button mylogout = (Button) findViewById(R.id.logout);
            mylogout.setVisibility(View.GONE);

        }//if

    }//onCreate


    /**
     * The intent to create the ListOfRounds activity
     * @param view The view that was clicked
     */
    public void goToListOfRounds(View view){
        Log.i(TAG, "gotToListOfRounds started");
//        Intent intentGoToListOfRounds = new Intent(AfterLogin.this,ListOfRounds.class);
//        startActivity(intentGoToListOfRounds);
    }


    /**
     * The intent to create the NewRound activity
     * @param view The view that was clicked
     */
    public void goToNewRound(View view){
        Log.i(TAG, "goToNewRound started");
//        Intent intentGoToNewRound = new Intent(AfterLogin.this,NewRound.class);
//        startActivity(intentGoToNewRound);
    }


    /**
     * The intent to create the ListOfLeagues activity
     * @param view The view that was clicked
     */
    public void goToLeagues(View view){
        Log.i(TAG, "goToNewRound started");
//        Intent intentGoToLeagues = new Intent(AfterLogin.this,ListOfLeagues.class);
//        startActivity(intentGoToLeagues);
    }


    /**
     * The intent to create the MainActivity activity.
     * The shared_preference with the keyword 'key' is removed.
     * @param view The view that was clicked
     */
    public void Logout(View view){
        Log.i(TAG, "Logout started");

        Preferences.removeFromPreferences(this, "loginID");

//        SharedPreferences sharedPreferences = PreferenceManager
//                .getDefaultSharedPreferences(this);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.remove("key");
//        editor.apply();
        LoginScript.loginID = "0";

        Intent intentGoToMain = new Intent(AfterLogin.this,Login.class);
        startActivity(intentGoToMain);
    }

    /*
    @Override
    public void onBackPressed() {

    }
*/


}//class AfterLoginGuest
