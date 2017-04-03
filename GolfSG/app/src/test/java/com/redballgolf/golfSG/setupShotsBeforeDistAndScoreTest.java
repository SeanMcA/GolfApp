package com.redballgolf.golfSG;

import com.redballgolf.golfSG.RoundOfGolf.Flag;
import com.redballgolf.golfSG.RoundOfGolf.Hole;
import com.redballgolf.golfSG.RoundOfGolf.Putt;
import com.redballgolf.golfSG.RoundOfGolf.Shot;
import com.redballgolf.golfSG.RoundOfGolf.Stroke;
import com.redballgolf.golfSG.RoundOfGolf.ShotInputScreen;


public class setupShotsBeforeDistAndScoreTest {
    Hole hole;
    Flag flag;
    Shot stroke1;
    Shot stroke2;
    Shot stroke3;
    Shot stroke4;
    Shot stroke5;
    Shot stroke6;


    public setupShotsBeforeDistAndScoreTest(){
        stroke2 = new Stroke(53.1244, -6.1244, ShotInputScreen.TEESHOTDRIVER);//567 yards to flag. Diff is 4.79. Score is -0.57
        stroke3 = new Stroke(53.1254, -6.1254, ShotInputScreen.FAIRWAY);//425 yards to flag. Diff is 4.20. Score is -0.92
        stroke4 = new Stroke(53.1264, -6.1264, ShotInputScreen.RECOVERY);//284 yards to flag. Diff is 4.20. Score is 0.12
        stroke5 = new Stroke(53.1274, -6.1274, ShotInputScreen.FAIRWAY);//142 yards to flag. Diff is 2.98. Score is -0.23
        stroke6 = new Putt(53.1283, -6.1283, ShotInputScreen.GREEN, 2);//42.54 feet to flag. Diff is 2.14. Score is ?

        hole = new Hole();
        hole.addShotToList(stroke2);
        hole.addShotToList(stroke3);
        hole.addShotToList(stroke4);
        hole.addShotToList(stroke5);
        hole.addShotToList(stroke6);

        flag = new Flag(53.1284, -6.1284);
    }

    public Hole getHole() {
        return hole;
    }

    public Flag getFlag() {
        return flag;
    }

    public Shot getStroke1() {
        return stroke1;
    }

    public Shot getStroke2() {
        return stroke2;
    }

    public Shot getStroke3() {
        return stroke3;
    }

    public Shot getStroke4() {
        return stroke4;
    }

    public Shot getStroke5() {
        return stroke5;
    }

    public Putt getStroke6() {
        return (Putt)stroke6;
    }
}
