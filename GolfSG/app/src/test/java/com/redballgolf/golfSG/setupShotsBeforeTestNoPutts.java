package com.redballgolf.golfSG;

import com.redballgolf.golfSG.RoundOfGolf.Flag;
import com.redballgolf.golfSG.RoundOfGolf.Hole;
import com.redballgolf.golfSG.RoundOfGolf.Stroke;
import com.redballgolf.golfSG.RoundOfGolf.ShotInputScreen;



public class setupShotsBeforeTestNoPutts {
    Hole hole;
    Flag flag;
    Stroke stroke1;
    Stroke stroke2;
    Stroke stroke3;
    Stroke stroke4;
    Stroke stroke5;
    Stroke stroke6;


    public setupShotsBeforeTestNoPutts(){
        stroke2 = new Stroke(53.1244, -6.1244, ShotInputScreen.TEESHOTDRIVER);//567 yards to flag. Diff is 4.79. Score is -0.57
        stroke3 = new Stroke(53.1254, -6.1254, ShotInputScreen.FAIRWAY);//425 yards to flag. Diff is 4.20. Score is -0.92
        stroke4 = new Stroke(53.1264, -6.1264, ShotInputScreen.RECOVERY);//284 yards to flag. Diff is 4.20. Score is 0.12
        stroke5 = new Stroke(53.1274, -6.1274, ShotInputScreen.FAIRWAY);//142 yards to flag. Diff is 2.98. Score is 1.98
        //stroke6 = new Stroke(0.0, 0.0, "Dummy");

        hole = new Hole();
        hole.addShotToList(stroke2);
        hole.addShotToList(stroke3);
        hole.addShotToList(stroke4);
        hole.addShotToList(stroke5);
        //hole.addShotToList(stroke6);

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

    //public Stroke getStroke6() { return stroke6;    }

}
