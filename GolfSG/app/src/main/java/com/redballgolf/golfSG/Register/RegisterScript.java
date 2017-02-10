package com.redballgolf.golfSG.Register;



import java.net.URLConnection;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.redballgolf.golfSG.Common.CreateUrlConnection;
import com.redballgolf.golfSG.Common.DataReturnedFromServer;
import com.redballgolf.golfSG.Common.WriteDataToLink;


public class RegisterScript extends AsyncTask<String,Void,String>{

    private Context context;
    private TextView regResultTV;
    private static final String TAG = "TEST";


    public RegisterScript(Context context, TextView regResultTV) {
        this.context = context;
        this.regResultTV = regResultTV;
    }

    protected void onPreExecute(){
        Log.i(TAG, "RegisterScript started");
    }

    @Override
    protected String doInBackground(String... args) {
        try{
            //The next two lines change for each Url request.
            String dataToSend = RegisterData.regData(args);
            String webUrl = RegisterData.registerUrl();

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
        Log.i(TAG, "Returned register result is: " + result);
        RegistrationServerResponse.output(context, result, regResultTV);
    }
}//class
