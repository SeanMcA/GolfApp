package com.redballgolf.golfSG.Login;



import java.net.URLConnection;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.redballgolf.golfSG.Common.CreateUrlConnection;
import com.redballgolf.golfSG.Common.DataReturnedFromServer;
import com.redballgolf.golfSG.Common.WriteDataToLink;


public class UpdateNewPasswordOnServer extends AsyncTask<String,Void,String>{

    private Context context;
    private static final String TAG = "TEST";


    public UpdateNewPasswordOnServer(Context context) {
        this.context = context;
    }

    protected void onPreExecute(){
        Log.i(TAG, "NewDetailsScript started");
    }


    /**
     * This method send the new password to the url along with the useremail address.
     * This is done so that the new email generated is inserted into the row with the same email
     * as the one being sent.
     * @param args The arguments sent in va the calling statement.
     * @return The result returned.
     */
    @Override
    protected String doInBackground(String... args) {
        try{
            //The next two lines change for each Url request.
            String dataToSend = UpdateNewPasswordData.Data(args);
            String webUrl = UpdateNewPasswordData.Url();

            URLConnection urlConnection = CreateUrlConnection.create(webUrl);
            WriteDataToLink.write(dataToSend, urlConnection);
            return DataReturnedFromServer.readDataFrom(urlConnection);
        }
        catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }
    }


    @Override
    protected void onPostExecute(String usernameAssociatedWithEmail){
        Log.i("TAG", "UpdateNewPwOnServer response: " + usernameAssociatedWithEmail);
        if(usernameAssociatedWithEmail.equals("That email address is not on record."))
        {
            Log.i(TAG, "No login returned");
        }
        else
        {
            Log.i(TAG,"Sending to sendEmailWithNewPassword");
            ForgotLoginDetails.sendEmailWithNewPassword(usernameAssociatedWithEmail);
        }
    }
}//class
