package com.redballgolf.golfSG;

import com.redballgolf.golfSG.RoundOfGolf.Shot;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.junit.Assert.assertEquals;

public class ShotTests {

    @Mock
    Shot shotMock = Mockito.mock(Shot.class);

    //@Ignore("This test will be ignored")
    @Test
    public void testSetShotDistance() {
        shotMock.setDistanceOfShot(2.4);
        assertEquals(2.4, shotMock.getDistanceOfShot(), 0.1);
    }
}
