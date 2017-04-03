package com.redballgolf.golfSG;

import com.redballgolf.golfSG.RoundOfGolf.Flag;
import com.redballgolf.golfSG.RoundOfGolf.Hole;
import com.redballgolf.golfSG.RoundOfGolf.Stroke;
import com.redballgolf.golfSG.RoundOfGolf.ShotInputScreen;


public class setupShotsBeforeTestPenalty {  Hole hole;
    Flag flag;
    Stroke stroke1;
    Stroke stroke2;
    Stroke stroke3;
    Stroke stroke4;
    Stroke stroke5;
    Stroke stroke6;


    public setupShotsBeforeTestPenalty(){
        stroke2 = new Stroke(53.1244, -6.1244, ShotInputScreen.TEESHOTDRIVER);//567 yards to flag. Diff is 4.79. Score is -0.57
        stroke3 = new Stroke(53.1254, -6.1254, ShotInputScreen.FAIRWAY);//425 yards to flag. Diff is 4.20. Score is -2.0
        stroke4 = new Stroke(53.1254, -6.1254, ShotInputScreen.PENALTY);//425 yards to flag. Diff is 4.20. Score is --
        //doesn't know where shot was hit from!!!
        stroke5 = new Stroke(53.1274, -6.1274, ShotInputScreen.FAIRWAY);//142 yards to flag. Diff is 2.98. Score is 0.22
        stroke6 = new Stroke(53.1283, -6.1283, ShotInputScreen.GREEN);//42.54 feet to flag. Diff is 2.14. Score is ?

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

    public Stroke getStroke1() {
        return stroke1;
    }

    public Stroke getStroke2() {
        return stroke2;
    }

    public Stroke getStroke3() {
        return stroke3;
    }

    public Stroke getStroke4() {
        return stroke4;
    }

    public Stroke getStroke5() {
        return stroke5;
    }

    public Stroke getStroke6() {
        return stroke6;
    }
}
