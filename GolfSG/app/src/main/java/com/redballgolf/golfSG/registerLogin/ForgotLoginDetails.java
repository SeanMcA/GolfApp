package com.redballgolf.golfSG.registerLogin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.sitting_room.golfsg.R;
import com.redballgolf.golfSG.Common.TopBar;

public class ForgotLoginDetails extends AppCompatActivity {
    private static Context mContext;
    public static String newPassword;
    public static String userEmail;
    EditText useremailEdittext;

    private static final String TAG = "TEST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this.getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_login_details);



//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        TopBar tb = new TopBar();
//        tb.create(toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);



        useremailEdittext = (EditText)findViewById(R.id.forgotDetailsEmail);


    }//onCreate


    /**
     * Gets the email address that the user entered and send this along with a new random
     * password to the web server to retrieve the users username and enter their new password.
     * @param view The view that was clicked
     */
    public void SendNewDetails(View view){
        userEmail = useremailEdittext.getText().toString();
        newPassword = GenerateNewPassword.create();
        Log.i(TAG,"newpassword is: " + newPassword);
        new NewLoginDetailsScript(this).execute(userEmail,newPassword);
    }



    /**
     * Send an email to the user with their username and a new password.
     * @param username The username retrieved from the web server that matches the email address the user entered.
     */
    protected static void sendEmailWithNewPassword(String username) {
//        Log.i(TAG,"FDS Send email started");
//        Log.i(TAG,"FDS user email is: " + userEmail);
        Intent i = GeneratePasswordEmailData.create(username);
//
//        String[] TO = {userEmail};
//        Intent emailIntent = new Intent(Intent.ACTION_SEND);
//        emailIntent.setData(Uri.parse("mailto:"));
//        emailIntent.setType("text/plain");
//        emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//
//        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
//        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your login details");
//        emailIntent.putExtra(Intent.EXTRA_TEXT, "Your Username is: "
//                + username + "\n"
//                + "Your new password is: "
//                + newPassword + "\n");
//
//        Intent i = Intent.createChooser(emailIntent, "Send mail...");
//        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(i);
        Log.i(TAG, "Finished sending email");

    }



}//class ForgotDetailsScreen
