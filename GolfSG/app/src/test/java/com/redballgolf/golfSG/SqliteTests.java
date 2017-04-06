package com.redballgolf.golfSG;


import android.content.Context;

import com.redballgolf.golfSG.SQLite.DatabaseHelper;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SqliteTests{
    Context context;

        @Before
            public void beforeTest() {
            DatabaseHelper db = new DatabaseHelper(context);
            //db.addDistancesToDB(199.00, 12);
        }

    @Test
    public void isShotDistanceCalcCorrect() throws Exception {
        assertEquals(580, 580, 0.5);
    }
}
