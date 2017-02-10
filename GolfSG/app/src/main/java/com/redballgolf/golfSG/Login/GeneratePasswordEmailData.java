package com.redballgolf.golfSG.Login;

import android.content.Intent;
import android.net.Uri;

import static com.redballgolf.golfSG.Login.ForgotLoginDetails.newPassword;
import static com.redballgolf.golfSG.Login.ForgotLoginDetails.userEmail;



public class GeneratePasswordEmailData {
    public static Intent create(String username){
        String[] TO = {userEmail};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your login details");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Your Username is: "
                + username + "\n"
                + "Your new password is: "
                + newPassword + "\n");

        Intent i = Intent.createChooser(emailIntent, "Send mail...");
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return i;
    }
}
