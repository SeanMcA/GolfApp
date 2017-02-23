package com.redballgolf.golfSG.Common;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.redballgolf.golfSG.GPS.GPS;
import com.redballgolf.golfSG.Login.AfterLogin;
import com.redballgolf.golfSG.Login.LoginRegisterOption;
import com.redballgolf.golfSG.R;
import com.redballgolf.golfSG.SharedPreferences.Preferences;

public class SplashScreen extends Activity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash_screen);
        final String LoggedInId = Preferences.getPreferences(this, "loginID");

        GPS gps = new GPS(this);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the LoginRegister-Activity. */
                if (LoggedInId.equals("0")) {
                        Intent mainIntent = new Intent(SplashScreen.this, LoginRegisterOption.class);
                        SplashScreen.this.startActivity(mainIntent);
                        SplashScreen.this.finish();
                    }else{
                    Intent mainIntent = new Intent(SplashScreen.this, AfterLogin.class);
                    SplashScreen.this.startActivity(mainIntent);
                    SplashScreen.this.finish();
                }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}