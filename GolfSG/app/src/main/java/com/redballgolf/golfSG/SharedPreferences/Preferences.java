package com.redballgolf.golfSG.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;


public class Preferences {
    public static final String MyPREFERENCES = "MyPrefs";

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
        final String DEFAULT_VALUE = "0";
        Log.i("TAG", "Preferences class - get: " + key);
        //SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String data = sharedPreferences.getString(key, DEFAULT_VALUE);
        Log.i("TAG", "Preferences class - get - returns: " + data);
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
