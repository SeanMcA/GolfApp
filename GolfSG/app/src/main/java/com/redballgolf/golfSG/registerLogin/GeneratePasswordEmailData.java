package com.redballgolf.golfSG.registerLogin;

import android.content.Intent;
import android.net.Uri;

import static com.redballgolf.golfSG.registerLogin.ForgotLoginDetails.newPassword;
import static com.redballgolf.golfSG.registerLogin.ForgotLoginDetails.userEmail;

/**
 * Created by sitting-room on 09/02/2017.
 */

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
