package com.redballgolf.golfSG.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.redballgolf.golfSG.Common.BaseActivity;
import com.redballgolf.golfSG.R;

public class ForgotLoginDetails extends BaseActivity {

    private static Context mContext;
    public static String newPassword;
    public static String userEmail;
    EditText useremailEdittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this.getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_login_details);

        useremailEdittext = (EditText)findViewById(R.id.forgotDetailsEmail);
    }//onCreate

    public void SendNewDetailsToServer(View view){
        userEmail = useremailEdittext.getText().toString();
        newPassword = GenerateNewPassword.create();
        Log.i("TAG","newpassword is: " + newPassword);
        new UpdateNewPasswordOnServer(this).execute(userEmail,newPassword);
    }

    protected static void sendEmailWithNewPassword(String usernameAssociatedWithEmail) {
        Intent i = GeneratePasswordEmailData.create(usernameAssociatedWithEmail);
        mContext.startActivity(i);
    }
}//class
