package com.redballgolf.golfSG.registerLogin;


import java.net.URLConnection;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.redballgolf.golfSG.Common.CreateUrlConnection;
import com.redballgolf.golfSG.Common.DataReturnedFromServer;
import com.redballgolf.golfSG.Common.WriteDataToLink;
import com.redballgolf.golfSG.SharedPreferences.Preferences;


public class LoginScript extends AsyncTask<String,Void,String>{

    private Context context;
    private TextView loginResultTextview;
    public static String loginID = "0";
    private static final String TAG = "TEST";


    public LoginScript(Context context, TextView loginResultTextview) {
        this.context = context;
        this.loginResultTextview = loginResultTextview;
        String PREF_NAME = "prefs";
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }


    protected void onPreExecute(){
        Log.i(TAG, "LoginScript started");
    }

    @Override
    protected String doInBackground(String... args) {
        try{
            String dataToSend = LoginData.loginData(args);
            String webUrl = LoginData.loginUrl();

            URLConnection urlConnection = CreateUrlConnection.create(webUrl);
            WriteDataToLink.write(dataToSend, urlConnection);
            return DataReturnedFromServer.readDataFrom(urlConnection);
        }
        catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result){
        Log.i("TAG", "LoginScript - Returned login result is: " + result);
        if(result.equals("0")){
            loginResultTextview.setText("Login Details are Incorrect");
        }
        else{
            Preferences.insert("loginID", result, context);//
            context.startActivity(new Intent(context, AfterLogin.class));
        }
        //LoginServerResponse.output(result, loginResultTextview, context);

    }
}//class
