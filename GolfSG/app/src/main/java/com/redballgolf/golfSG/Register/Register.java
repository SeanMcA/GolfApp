package com.redballgolf.golfSG.Register;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.redballgolf.golfSG.Common.BaseActivity;
import com.redballgolf.golfSG.R;

public class Register extends BaseActivity {
    EditText regUsernameET , regEmailET, regPasswordET, regConfirmPasswordET;
    TextView regResultTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regUsernameET = (EditText)findViewById(R.id.usernameET);
        regEmailET = (EditText)findViewById(R.id.regEmailET);
        regPasswordET = (EditText)findViewById(R.id.regPasswordET);
        regConfirmPasswordET = (EditText)findViewById(R.id.regConfirmPasswordET);
        regResultTV = (TextView)findViewById(R.id.regResultTV);
    }//onCreate

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
