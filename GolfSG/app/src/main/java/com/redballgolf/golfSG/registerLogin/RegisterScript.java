package com.redballgolf.golfSG.registerLogin;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.sitting_room.golfsg.R;


public class RegisterScript extends AsyncTask<String,Void,String>{

    private Context context;
    private TextView loginRegisterResultTextview;
    private static final String TAG = "TEST";


    public RegisterScript(Context context, TextView loginRegisterResultTextview) {
        this.context = context;
        this.loginRegisterResultTextview = loginRegisterResultTextview;
        String PREF_NAME = "prefs";
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }





    protected void onPreExecute(){
        Log.i(TAG, "RegisterScript started");
    }



    @Override
    protected String doInBackground(String... arg0) {

        try{
            String username = arg0[0];
            String password = arg0[1];
            String email = arg0[2];

            String link="http://zelusit.com/androidRegister.php";
            String data  = URLEncoder.encode("sentUsername", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
            //encoding prevents a SQL injection attack!
            data += "&" + URLEncoder.encode("sentPassword", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
            data += "&" + URLEncoder.encode("sentEmail", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
            //THE part above changes...interface?!!!!
            //OR...put part below in super class and inherit?!...can't as this class is already inheriting!


            //Could try and put the next part in a class of its own: public String UrlConnection(String data){return string;}
            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);//Set the DoOutput flag to true if you intend to use the URL connection for output, false if not.
            //setDoOutput(true) is used for POST and PUT requests. If it is false then it is for using GET requests.


            //The last thing you need to do is to write this data to the link.
            //After writing, you need to open stream to receive the responded data.
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            wr.flush();
            //When you write data to a stream, it is not written immediately, and it is buffered.
            //So use flush() when you need to be sure that all your data from buffer is written.
            //In this case it is written to the OutputStream.

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                sb.append(line);
                break;
            }
            return sb.toString();
        }//try
        catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }//catch
    }


    //This part changes........interface?
    @Override
    protected void onPostExecute(String result){
        //Log.i(TAG, "Returned login is: " + loginID);
        Log.i(TAG, "Returned register result is: " + result);
        if(result.equals("Username is not available"))
        {
            //Log.i(TAG, "No login returned");
            loginRegisterResultTextview.setText(R.string.UsernameAlreadyInUse);
        }
        else if(result.equals(context.getString(R.string.EmailAlreadyInUse)))
        {
            loginRegisterResultTextview.setText(R.string.EmailAlreadyInUse);
        }
        else
        {
            loginRegisterResultTextview.setText(R.string.YouHaveBeenRegistered);
            context.startActivity(new Intent(context, Login.class));
        }
    }//onPostExecute



}
