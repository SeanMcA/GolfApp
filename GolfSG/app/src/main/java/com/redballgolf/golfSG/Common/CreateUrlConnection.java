package com.redballgolf.golfSG.Common;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Same code for all classes
 */

public class CreateUrlConnection {
    public static URLConnection create(String link) throws IOException{
        URL url = new URL(link);
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        return conn;
    }
}
