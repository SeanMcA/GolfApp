package com.redballgolf.golfSG.RoundOfGolf;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class GetListOfCourses {
    String TAG = "GOLF";
    static String response = null;
    public final static int GET = 1;
    public final static int POST = 2;

    //Constructor with no parameter
    public GetListOfCourses() {
    }


    public String retrieveCourses(String urladdress) {
        URL url;
        String response = "";
        try {
            url = new URL(urladdress);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();//Gets the status code from an HTTP response message.

            if (responseCode == HttpsURLConnection.HTTP_OK) {//HTTP_OK = 200
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                response = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

}
