package com.redballgolf.golfSG.registerLogin;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.redballgolf.golfSG.R;

/**
 * This class decideds what to do with the data returned from the server.
 * This class will contain decisions unique to the data being returned.
 */

public class RegistrationServerResponse {
    //extends AppCompatActivity
    //TextView regResultTV = (TextView)findViewById(R.id.regResultTV);
    public static void output(Context context, String result, TextView regResultTV) {
        if(result.equals(context.getString(R.string.UsernameNotAvailable)))
        {
            regResultTV.setText(R.string.UsernameNotAvailable);
        }
        else if(result.equals(context.getString(R.string.EmailAlreadyInUse)))
        {
            regResultTV.setText(R.string.EmailAlreadyInUse);
        }
        else
        {
            regResultTV.setText(R.string.YouHaveBeenRegistered);
            context.startActivity(new Intent(context, Login.class));
        }
    }
}
