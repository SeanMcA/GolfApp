package com.redballgolf.golfSG.Common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLConnection;

/**
 * Created by sitting-room on 09/02/2017.
 */

public class DataReturnedFromServer {
    public static String readDataFrom(URLConnection urlConnection) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null)
        {
            stringBuilder.append(line);
            break;
        }
        return stringBuilder.toString();
    }
}
