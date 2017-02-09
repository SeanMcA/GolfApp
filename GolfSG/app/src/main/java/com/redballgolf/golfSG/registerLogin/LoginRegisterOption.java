package com.redballgolf.golfSG.registerLogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.redballgolf.golfSG.R;

public class LoginRegisterOption extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register_option);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void goToLoginScreen(View view){
        Intent goToLoginScreen = new Intent(LoginRegisterOption.this,Login.class);
        startActivity(goToLoginScreen);
    }

    public void goToRegisterScreen(View view){
        Intent goToRegisterScreen = new Intent(LoginRegisterOption.this,Register.class);
        startActivity(goToRegisterScreen);
    }

}
