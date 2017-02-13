package com.redballgolf.golfSG.RoundOfGolf;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class GetListOfCourses {

    //Constructor with no parameter
    public GetListOfCourses() {
    }


    public String retrieveCourses(String urladdress) {
        URL url;
        String response = "";
        try {
            url = new URL(urladdress);
            HttpURLConnection conn = createHttpConnection(url);
            response = writeServerResponseToString(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    private HttpURLConnection createHttpConnection(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(15000);
        conn.setConnectTimeout(15000);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("GET");
        return conn;
    }

    private String writeServerResponseToString(HttpURLConnection conn) throws IOException {
        String data="";
        int responseCode = conn.getResponseCode();//Gets the status code from an HTTP response message.

        if (responseCode == HttpsURLConnection.HTTP_OK) {//HTTP_OK = 200
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = br.readLine()) != null) {
                data += line;
            }
        } else {
            data = "";
        }
        return data;
    }

}//class

