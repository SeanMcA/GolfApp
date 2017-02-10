package com.redballgolf.golfSG.Login;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.redballgolf.golfSG.Common.BaseActivity;
import com.redballgolf.golfSG.R;

public class Login extends BaseActivity {

    private EditText usernameField,passwordField;
    private TextView loginResultTextview;//If details are incorrect then an error message for the user will be displayed here.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameField = (EditText)findViewById(R.id.loginUsername);
        passwordField = (EditText)findViewById(R.id.loginPassword);
        loginResultTextview = (TextView)findViewById(R.id.loginResult);
    }//onCreate


    public void login(View view){
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        new LoginScript(this,loginResultTextview).execute(username,password);
    }

    public void goToLoginDetailsScreen(View view){
        Intent intentGoToForgotDetailsScreen = new Intent(Login.this,ForgotLoginDetails.class);
        startActivity(intentGoToForgotDetailsScreen);
    }
}//class
