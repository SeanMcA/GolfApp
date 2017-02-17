package com.redballgolf.golfSG.RoundOfGolf;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.redballgolf.golfSG.Common.BaseActivity;
import com.redballgolf.golfSG.R;
import com.redballgolf.golfSG.SQLite.DatabaseHelper;

import java.util.ArrayList;

public class HoleSummary extends BaseActivity {
    private String hit_from;
    private String shot_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hole_summary);

        TextView holeSummaryHeader = (TextView) findViewById(R.id.hole_summary_header);
        holeSummaryHeader.setText("Hole " + Hole.getHoleNumber());


        TableLayout tableLayout = (TableLayout) findViewById(R.id.tablelayout);
        TableRow rowHeader = getRowHeaders();
        tableLayout.addView(rowHeader);
        getSummaryDataForHole(tableLayout);
    }//onCreate

    private TableRow getRowHeaders() {
        TableRow rowHeader = new TableRow(this);
        rowHeader.setBackgroundColor(Color.parseColor("#c0c0c0"));
        rowHeader.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
        TableLayout.LayoutParams.WRAP_CONTENT));
        String[] headerText = {"Hit From", "Shot Score"};
        for(
        String columnHeaders
        :headerText)

        {
            TextView tv = new TextView(this);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setGravity(Gravity.LEFT);
            tv.setTextSize(18);
            tv.setPadding(5, 5, 5, 5);
            tv.setText(columnHeaders);
            rowHeader.addView(tv);
        }
        return rowHeader;
    }

    private void getSummaryDataForHole(TableLayout tableLayout){
        ArrayList <String> holeSummaryData = Hole.getHoleSummary();
        int numberOfShots = holeSummaryData.size();
        for(int i = 0; i < numberOfShots; i = i + 2) {
            hit_from = holeSummaryData.get(i);
            shot_score = holeSummaryData.get(i + 1);
            displaySummaryDataInTable(tableLayout, hit_from, shot_score);
        }
    }

    private void displaySummaryDataInTable(TableLayout tableLayout, String hit_from, String shot_score){
        // data rows
        TableRow row = new TableRow(this);
        row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        String[] colText = {hit_from, shot_score};
        for (String text : colText) {
            TextView tv = new TextView(this);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            //tv.setGravity(Gravity.CENTER);
            tv.setGravity(Gravity.LEFT);
            tv.setTextSize(16);
            tv.setPadding(5, 5, 255, 5);
            tv.setText(text);
            row.addView(tv);
        }
        tableLayout.addView(row);
    }


    public void returnToMain(View view){
        Intent intentReturnToMain = new Intent(HoleSummary.this,ShotInputScreen.class);
        startActivity(intentReturnToMain);
    }

    public void endRound(View view){
//        Log.i(TAG, "end round started**");
//        Intent intentGetRoundSummary = new Intent(HoleSummary.this, RoundSummary.class);
//        intentGetRoundSummary.putExtra("roundID", roundID);
//        startActivity(intentGetRoundSummary);
    }

}//class
