package com.redballgolf.golfSG.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.redballgolf.golfSG.registerLogin.LoginScript;


public class Preferences {
    public static final String MyPREFERENCES = "MyPrefs";
    //SharedPreferences sharedpreferences;

    public static void insert(String key, String data, Context context){
        Log.i("TAG", "Preferences class - insert");
        Log.i("TAG", "Preferences class - insert key: " + key);
        Log.i("TAG", "Preferences class - insert data: " + data);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, data);
        editor.apply();
    }

    public static String getPreferences(Context context, String key){
        Log.i("TAG", "Preferences class - get: " + key);
        //SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String data = sharedPreferences.getString(key, "0");//0 is the default if no response is given
        return data;
    }

    public static void removeFromPreferences(Context context, String key){
        Log.i("TAG", "Preferences class - remove: " + key);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }


}
