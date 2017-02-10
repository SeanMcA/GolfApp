package com.redballgolf.golfSG.Login;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by sitting-room on 09/02/2017.
 */

class LoginDataToSend {
    public static String loginData(String... args) throws UnsupportedEncodingException {
        String username = args[0];
        String password = args[1];

        String data  = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
        data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
        return data;
    }

    public static String loginUrl(){
        return "http://zelusit.com/androidlogin.php";
    }
}