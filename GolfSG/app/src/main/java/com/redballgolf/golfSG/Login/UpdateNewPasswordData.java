package com.redballgolf.golfSG.Login;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;



public class UpdateNewPasswordData {
    public static String Data(String... args) throws UnsupportedEncodingException {
        String userEmail = args[0];
        String newPassword = args[1];

        String data  = URLEncoder.encode("userEmail", "UTF-8") + "=" + URLEncoder.encode(userEmail, "UTF-8");
        data += "&" + URLEncoder.encode("newPassword", "UTF-8") + "=" + URLEncoder.encode(newPassword, "UTF-8");
        return data;
    }

    public static String Url(){
        return "http://zelusit.com/androidNewDetails.php";
    }
}

