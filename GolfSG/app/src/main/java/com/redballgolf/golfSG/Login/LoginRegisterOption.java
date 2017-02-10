package com.redballgolf.golfSG.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.redballgolf.golfSG.Common.BaseActivity;
import com.redballgolf.golfSG.R;
import com.redballgolf.golfSG.Register.Register;

public class LoginRegisterOption extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register_option);
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
