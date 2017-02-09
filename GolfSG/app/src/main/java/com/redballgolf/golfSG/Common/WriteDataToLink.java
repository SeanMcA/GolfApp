package com.redballgolf.golfSG.Common;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URLConnection;

/**
 * Same for all classes
 */

public class WriteDataToLink {
    public static Boolean write(String data, URLConnection conn) throws IOException{
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write( data );
        wr.flush();
        return true;
    }
}
