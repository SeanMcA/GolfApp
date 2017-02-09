package com.redballgolf.golfSG.registerLogin;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * This class contains code unique to the specific requirements of the individual request.
 * It should be tailored to sui what is needed.
 */

public class RegisterData {

    public static String regData(String... args) throws UnsupportedEncodingException {
        String username = args[0];
        String password = args[1];
        String email = args[2];

        String data = URLEncoder.encode("sentUsername", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
        data+="&"+URLEncoder.encode("sentPassword","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
        data+="&"+URLEncoder.encode("sentEmail","UTF-8")+"="+URLEncoder.encode(email,"UTF-8");
            return data;
    }

    public static String registerUrl(){
        return "http://zelusit.com/androidRegister.php";
    }
}
