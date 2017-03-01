package com.redballgolf.golfSG;

import com.redballgolf.golfSG.RoundOfGolf.Flag;
import com.redballgolf.golfSG.RoundOfGolf.Hole;
import com.redballgolf.golfSG.RoundOfGolf.Shot;
import com.redballgolf.golfSG.RoundOfGolf.ShotScore;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class MockitoTrial {

    //Creation of Mock objects.
    @Mock
    Shot shotMock = Mockito.mock(Shot.class);
    Hole holeMock = Mockito.mock(Hole.class);
    Flag flagMock = Mockito.mock(Flag.class);

    //Initialise Mock objects.
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();



    @Test
    public void testQuery()  {



        ShotScore.calculateEachShotOnThis(holeMock, flagMock);
    }

}
