package com.redballgolf.golfSG.registerLogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sitting_room.golfsg.R;
import com.redballgolf.golfSG.GPS.startGPS;

public class Register extends AppCompatActivity {
    EditText regUsernameET , regEmailET, regPasswordET, regConfirmPasswordET;
    TextView regResultTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        regUsernameET = (EditText)findViewById(R.id.usernameET);
        regEmailET = (EditText)findViewById(R.id.regEmailET);
        regPasswordET = (EditText)findViewById(R.id.regPasswordET);
        regConfirmPasswordET = (EditText)findViewById(R.id.regConfirmPasswordET);
        regResultTV = (TextView)findViewById(R.id.regResultTV);


    }

    public void register(View view){
        String username = regUsernameET.getText().toString();
        String password = regPasswordET.getText().toString();
        String confirmPassword = regConfirmPasswordET.getText().toString();
        String email = regEmailET.getText().toString();

        if(password.equals(confirmPassword))
        {
            new RegisterScript(this, regResultTV).execute(username, password, email);
        }
        else
        {
            regResultTV.setText(getString(R.string.password_doesnt_match));
        }
    }

}
