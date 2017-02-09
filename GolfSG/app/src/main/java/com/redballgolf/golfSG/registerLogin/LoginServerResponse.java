package com.redballgolf.golfSG.registerLogin;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import com.redballgolf.golfSG.SharedPreferences.Preferences;


public class LoginServerResponse {
    public static void output(String result, TextView loginResultTextview, Context context){
        String loginID = result;
        Log.i("TAG", "LoginServerResponse - Returned login is: " + loginID);
        if(result.equals("0"))
        {
            //Log.i(TAG, "No login returned");
            loginResultTextview.setText("Login Details are Incorrect");
        }
        else
        {
            Preferences.insert("loginID", loginID, context);
//            SharedPreferences sharedPreferences = PreferenceManager
//                    .getDefaultSharedPreferences(context);
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString("key", loginID);
//            editor.apply();
//
            context.startActivity(new Intent(context, AfterLogin.class));
        }
    }
}
