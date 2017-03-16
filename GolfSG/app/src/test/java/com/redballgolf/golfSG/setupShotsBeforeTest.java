package com.redballgolf.golfSG;

import com.redballgolf.golfSG.RoundOfGolf.Flag;
import com.redballgolf.golfSG.RoundOfGolf.Hole;
import com.redballgolf.golfSG.RoundOfGolf.Shot;
import com.redballgolf.golfSG.RoundOfGolf.ShotInputScreen;


public class setupShotsBeforeTest {
    Hole hole;
    Flag flag;
    Shot shot1;
    Shot shot2;
    Shot shot3;
    Shot shot4;
    Shot shot5;
    Shot shot6;


    public setupShotsBeforeTest(){
        shot2 = new Shot(53.1244, -6.1244, ShotInputScreen.TEESHOTDRIVER, 0, false);//567 yards to flag. Diff is 4.79. Score is -0.57
        shot3 = new Shot(53.1254, -6.1254, ShotInputScreen.FAIRWAY, 0, false);//425 yards to flag. Diff is 4.20. Score is -0.92
        shot4 = new Shot(53.1264, -6.1264, ShotInputScreen.RECOVERY, 0, false);//284 yards to flag. Diff is 4.20. Score is 0.12
        shot5 = new Shot(53.1274, -6.1274, ShotInputScreen.FAIRWAY, 0, false);//142 yards to flag. Diff is 2.98. Score is -0.23
        shot6 = new Shot(53.1283, -6.1283, ShotInputScreen.PUTT, 2, false);//42.54 feet to flag. Diff is 2.14. Score is ?

        hole = new Hole();
        hole.addShotToList(shot2);
        hole.addShotToList(shot3);
        hole.addShotToList(shot4);
        hole.addShotToList(shot5);
        hole.addShotToList(shot6);

        flag = new Flag(53.1284, -6.1284);
    }

    public Hole getHole() {
        return hole;
    }

    public Flag getFlag() {
        return flag;
    }

    public Shot getShot1() {
        return shot1;
    }

    public Shot getShot2() {
        return shot2;
    }

    public Shot getShot3() {
        return shot3;
    }

    public Shot getShot4() {
        return shot4;
    }

    public Shot getShot5() {
        return shot5;
    }

    public Shot getShot6() {
        return shot6;
    }
}
