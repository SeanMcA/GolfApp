package com.redballgolf.golfSG.registerLogin;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sitting_room.golfsg.R;
import com.redballgolf.golfSG.Common.TopBar;

public class Login extends AppCompatActivity {

    private EditText usernameField,passwordField;
    private TextView loginResultTextview;//If details are incorrect then an error message for the user will be displayed here.
    private static final String TAG = "TEST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        TopBar tb = new TopBar();
//        tb.create(toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle(sentLeagueName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        usernameField = (EditText)findViewById(R.id.loginUsername);
        passwordField = (EditText)findViewById(R.id.loginPassword);
        loginResultTextview = (TextView)findViewById(R.id.loginResult);

    }//onCreate


    /**
     * This method calls the LoginScript class and passes the usernama and password
     * that the user entered.
     * @param view The view that was clicked
     */
    public void login(View view){
        Log.i("TAG", "Login scr - login started");
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        Log.i("TAG", "Username is : " + username);
        //Call execute in LoginScript class to run in background and return mesage if details are incorrect.
        // If details are correct the set the loginID to the ID rerturned from the DB.
        //Then launch the AfterLoginGuest class.
        new LoginScript(this,loginResultTextview).execute(username,password);
    }


    /**
     * This method starts the Intent to go to theFotgotDetailsScreen.
     * @param view The view that was clicked.
     */
    public void ForgotLoginDteails(View view){
        Intent intentGoToForgotDetailsScreen = new Intent(Login.this,ForgotLoginDetails.class);
        startActivity(intentGoToForgotDetailsScreen);
    }

}//class Login
