package com.redballgolf.golfSG.registerLogin;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;


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
            String dataToSend = RegisterData.regData(args);
            String webUrl = RegisterData.registerUrl();

            URLConnection urlConnection = createUrlConnection(webUrl);
            writeDataToLink(dataToSend, urlConnection);

            return returnedData(urlConnection);
        }//try
        catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }//catch
    }


    @Override
    protected void onPostExecute(String result){
        Log.i(TAG, "Returned register result is: " + result);
        RegistrationServerResponse.output(context, result, regResultTV);
    }//onPostExecute



    URLConnection createUrlConnection(String link) throws IOException {
        URL url = new URL(link);
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        return conn;
    }

    Boolean writeDataToLink(String data, URLConnection conn) throws IOException {
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write( data );
        wr.flush();
        return true;
    }

    String returnedData(URLConnection urlConnection) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null)
        {
            stringBuilder.append(line);
            break;
        }
        return stringBuilder.toString();
    }

}
