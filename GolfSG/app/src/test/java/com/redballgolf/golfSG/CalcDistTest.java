package com.redballgolf.golfSG;

import com.redballgolf.golfSG.RoundOfGolf.CalculateDistance;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CalcDistTest {

    @Test
    public void isDistCalcCorrect() throws Exception {
        double distance = CalculateDistance.distanceIs(53.1244, -6.1244, 53.1284, -6.1284);
        assertEquals(567, distance, 0.4);
    }

}
