package com.redballgolf.golfSG.RoundOfGolf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Hole {
    private static List<Shot> shotList = new LinkedList<>();
    private static int holeNumber = 1;
    private int roundID;

    public Hole(Round round) {
        round.addHoleToRoundsHoleList(this);
    }


    public void addShotToHolesShotList(Shot shot) {
        shotList.add(shot);
    }

    public List<Shot> getShotList() {
        return shotList;
    }

    public static int getHoleNumber() {
        return holeNumber;
    }

    public static void setHoleNumber(int holeNumber) {
        Hole.holeNumber = holeNumber;
    }


    public static ArrayList getHoleSummary() {
        ArrayList holeSummary = new ArrayList();
        //This can be changed to iterate through List and get data.!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Iterator listIterator = shotList.listIterator();
        while (listIterator.hasNext()) {
            String lie = (((Shot) listIterator.next()).getLie());
            String score = String.valueOf((((Shot) listIterator.next()).getShotScore()));
            holeSummary.add(lie);
            holeSummary.add(score);
        }
        return holeSummary;

//
//        String[] data = new String[2];
//        SQLiteOpenHelper myTestDatabaseHelper = new DatabaseHelper(context);
//        //We don’t need to write to the database so we’re using getReadableDatabase().
//        SQLiteDatabase db = myTestDatabaseHelper.getReadableDatabase();
//
//        String selectQuery = "SELECT * FROM shot WHERE hole = " + Hole.getHoleNumber() + " AND round_id =" + Round.getRoundID() + ";";
//        Log.i("TAG", "HoleSummary query is: " + selectQuery);
//        Cursor cursor = db.rawQuery(selectQuery, null);
//        if (cursor.getCount() > 0) {
//            while (cursor.moveToNext()) {
//                // Read columns data
//                String hit_from = cursor.getString(cursor.getColumnIndex("hit_from"));
//                if (hit_from.equals("green")) {
//                    hit_from = "putt";
//                } else if (hit_from.equals("rightRough")) {
//                    hit_from = "Rough";
//                } else if (hit_from.equals("rightTrees")) {
//                    hit_from = "Trees";
//                } else if (hit_from.equals("rightBunker")) {
//                    hit_from = "Bunker";
//                } else if (hit_from.equals("tee_shot_iron")) {
//                    hit_from = "Tee Shot Iron";
//                } else if (hit_from.equals("tee_shot_driver")) {
//                    hit_from = "Tee Shot Driver";
//                }
//                String shot_score = cursor.getString(cursor.getColumnIndex("shot_score"));
//                data[0] = hit_from;
//                data[1] = shot_score;
//            }
//        }
//        db.setTransactionSuccessful();
//        cursor.close();
//        return data;
    }
}
