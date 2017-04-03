package com.redballgolf.golfSG;

import com.redballgolf.golfSG.RoundOfGolf.Flag;
import com.redballgolf.golfSG.RoundOfGolf.Hole;
import com.redballgolf.golfSG.RoundOfGolf.ShotScore;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ShotDistTestPenalty { setupShotsBeforeTest setup = new setupShotsBeforeTest();
    Hole hole = setup.getHole();
    Flag flag = setup.getFlag();

    @Before
    public void beforeTest(){
        ShotScore.getAndSetDistanceOfShot(hole, flag);
    }


    @Test
    public void isShotDistanceCalcCorrect() throws Exception {
        assertEquals(567, setup.getStroke2().getDistanceOfShot(), 0.5);
        assertEquals(425, setup.getStroke3().getDistanceOfShot(), 0.5);
        assertEquals(284, setup.getStroke4().getDistanceOfShot(), 0.5);
        assertEquals(142, setup.getStroke5().getDistanceOfShot(), 0.5);
        assertEquals(42.5, setup.getStroke6().getDistanceOfShot(), 0.5);//this is a putt
    }


    @Test
    public void isShotDiffCorrect() throws Exception {
        assertEquals(4.79, setup.getStroke2().getShotDifficultyRating(), 0.01);
        assertEquals(4.20, setup.getStroke3().getShotDifficultyRating(), 0.01);
        assertEquals(4.20, setup.getStroke4().getShotDifficultyRating(), 0.01);
        assertEquals(2.98, setup.getStroke5().getShotDifficultyRating(), 0.01);
        assertEquals(2.14, setup.getStroke6().getShotDifficultyRating(), 0.01);//this is a putt
    }

    @Test
    public void isShotScoreCorrect() throws Exception {
        ShotScore.calculateFinalShotScore(hole);
        assertEquals(-0.41, setup.getStroke2().getShotScore(), 0.01);
        assertEquals(-2, setup.getStroke3().getShotScore(), 0.01);
        assertEquals(0, setup.getStroke4().getShotScore(), 0.01);
        assertEquals(0.22, setup.getStroke5().getShotScore(), 0.01);
        assertEquals(0.14, setup.getStroke6().getShotScore(), 0.01);//this is a putt. It is a two putt.
    }
}
