package com.redballgolf.golfSG.RoundOfGolf;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by sitting-room on 12/02/2017.
 */

public class ExtractCourses {

    /**
     * This method takes the json data returned from the web database and iteraties through it
     * and extracts the relevant information. For each iteration it puts the data in a HashMap called leagues and then
     * puts that HashMap in to an ArrayList called leagueList.
     * Data is also put into arrays which are used to store data that will be need but not displayed.
     * @param json The json data returned from the web database.
     * @return Teh arrayList containing HashMaps of the paresd json data.
     */
    //Could this be in its own class????????????
    public static ArrayList<HashMap<String, String>> ParseJSON(String json) {
        if (json != null) {
            try {
                //ArrayList for ListView
                ArrayList<HashMap<String, String>> leagueList = new ArrayList<>();

                JSONObject jsonObj = new JSONObject(json);

                // Getting JSON Array node
                JSONArray leagues = jsonObj.getJSONArray(TAG_lEAGUEINFO);


                //initialise arrays.
                leagueNamesArray = new String[leagues.length()];
                leagueIDsArray = new Integer[leagues.length()];
                leagueTypeArray = new String[leagues.length()];
                leagueStartDateArray = new String[leagues.length()];
                leagueEndDateArray = new String[leagues.length()];

                //loop through all leagues
                for (int i = 0; i < leagues.length(); i++) {
                    Log.i(TAG, "leagues length is: " + leagues.length());
                    JSONObject c = leagues.getJSONObject(i);

                    String leagueName = c.getString(TAG_LEAGUE_NAME);
                    Integer leagueid = c.getInt(TAG_LEAGUEID);
                    String leagueStartDate = c.getString(TAG_LEAGUE_START_DATE);
                    String leagueEndDate = c.getString(TAG_LEAGUE_END_DATE);
                    String leagueType = c.getString(TAG_LEAGUE_TYPE);
                    String leagueIdAsString = Integer.toString(leagueid);
                    String leagueCode = "Code: " + leagueIdAsString;


                    //Convert date String to Date object and send to daysLeft method
                    //The result is checked to see if the end date has passed and if it has
                    //let the user know. If it has not then let the user know how many days are left
                    //beofre the league will end
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date endDate = df.parse(leagueEndDate);
                        daysLeft = daysBetween(endDate);
                        if(daysLeft >= 0)
                        {
                            stringDaysLeft = "(" + daysLeft.toString() + " days left.)";
                        }
                        else
                        {
                            stringDaysLeft = "League finished.";
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                    String leagueTypeDisplay;
                    if(leagueType.equals("sg_driving")){
                        leagueTypeDisplay = "Driving league";
                    }else if(leagueType.equals("sg_long_game")){
                        leagueTypeDisplay = "Long Game League";
                    }else if(leagueType.equals("sg_short_game")){
                        leagueTypeDisplay = "Short Game League";
                    }else if(leagueType.equals("sg_putting")){
                        leagueTypeDisplay = "Putting League";
                    }else leagueTypeDisplay ="";
                    Log.i(TAG,"League type is: " + leagueTypeDisplay);

                    //Log.i(TAG, "league id is: " + leagueid);

                    // tmp hashmap for single league. This will be put in to the leagueList ArrayList.
                    HashMap<String, String> league = new HashMap<>();

                    // adding each child node to HashMap key => value
                    league.put(TAG_LEAGUE_NAME, leagueName);
                    league.put(TAG_LEAGUE_TYPE, leagueTypeDisplay);
                    league.put(TAG_LEAGUEID, leagueCode);
                    league.put(TAG_LEAGUE_DAYS_LEFT, stringDaysLeft);


                    //Put league ids in an array and the positions will be used when a league
                    //is clicked in the listview to identify the id for the league which has been clicked.
                    leagueNamesArray[i] = leagueName;
                    leagueIDsArray[i] = leagueid;
                    leagueTypeArray[i] = leagueType;
                    leagueStartDateArray[i] = leagueStartDate;
                    leagueEndDateArray[i] = leagueEndDate;




                    // adding the HashMap league to ArrayList leagueslist.
                    leagueList.add(league);
                }
                return leagueList;
            } catch (JSONException e) {
                e.printStackTrace();
                //Log.i(TAG,"No Working" + e.printStackTrace());
                return null;
            }
        } else {
            Log.i(TAG, "ServiceHandler, Couldn't get any data from the url");
            return null;
        }
    }//ParsonJson
}
