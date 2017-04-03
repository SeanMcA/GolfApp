package com.redballgolf.golfSG;

import com.redballgolf.golfSG.RoundOfGolf.Flag;
import com.redballgolf.golfSG.RoundOfGolf.Hole;
import com.redballgolf.golfSG.RoundOfGolf.ShotScore;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ShotTestNoPutt {
    setupShotsBeforeTestNoPutts setup = new setupShotsBeforeTestNoPutts();
    Hole hole = setup.getHole();
    Flag flag = setup.getFlag();

    @Before
    public void beforeTest(){
        ShotScore.calculateShotDistanceAndDifficulty(hole, flag, false);
    }


    @Test
    public void isShotDistanceCalcCorrect() throws Exception {
        assertEquals(580, setup.getStroke2().getDistanceOfShot(), 0.5);
        assertEquals(440, setup.getStroke3().getDistanceOfShot(), 0.5);
        assertEquals(300, setup.getStroke4().getDistanceOfShot(), 0.5);
        assertEquals(160, setup.getStroke5().getDistanceOfShot(), 0.5);
    }


    @Test
    public void isShotDiffCorrect() throws Exception {
        assertEquals(4.79, setup.getStroke2().getShotDifficultyRating(), 0.01);
        assertEquals(4.20, setup.getStroke3().getShotDifficultyRating(), 0.01);
        assertEquals(4.20, setup.getStroke4().getShotDifficultyRating(), 0.01);
        assertEquals(2.98, setup.getStroke5().getShotDifficultyRating(), 0.01);
    }

    @Test
    public void isShotScoreCorrect() throws Exception {
        ShotScore.calculateShotScore(hole, false);
        assertEquals(-0.41, setup.getStroke2().getShotScore(), 0.01);
        assertEquals(-1, setup.getStroke3().getShotScore(), 0.01);
        assertEquals(0.22, setup.getStroke4().getShotScore(), 0.01);
        assertEquals(1.98, setup.getStroke5().getShotScore(), 0.01);
    }
}

