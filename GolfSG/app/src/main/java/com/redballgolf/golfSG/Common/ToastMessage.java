package com.redballgolf.golfSG.Common;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by sitting-room on 12/02/2017.
 */

public class ToastMessage {
    public static void displayLongToastMessage(Context context, String message){
        Toast toast= Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 150);
        toast.show();
    }
}
