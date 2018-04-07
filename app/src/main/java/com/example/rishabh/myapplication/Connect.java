package com.example.rishabh.myapplication;

import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Weston Newby on 11/3/2014.
 */
public class Connect {

    public static final String TAG_SUCCESS = "success";
    public static final String TAG_SJSUID = "sjsuid";
    public static final String TAG_OPTION = "option";
    public static final String TAG_RATING_ID = "ratingID";
    public static final String TAG_RATINGS = "ratings";
    public static final String TAG_POLL_ID = "pollID";
    public static final String TAG_POLLS = "polls";
    public static final String TAG_MAX_RATE = "maxRate";
    public static final String TAG_DATE = "date";
    public static final String TAG_TITLE = "title";


    public static boolean newUser(String sjsuid, String first_name, String last_name, String password) {

        JSONParser jsonParser = new JSONParser();
        String url_create_user = "http://ec2-54-200-47-19.us-west-2.compute.amazonaws.com/create_user.php";

        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("sjsuid", sjsuid));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("first_name", first_name));
        params.add(new BasicNameValuePair("last_name", last_name));

        // getting JSON Object
        // Note that create product url accepts POST method
        JSONObject json = jsonParser.makeHttpRequest(url_create_user,
                "POST", params);

        // check log cat fro response
        Log.d("Create Response", json.toString());

        // check for success tag
        try {
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // successfully created product
                return true;

            } else {
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int login(String sjsuid, String password) {
        int success;
        JSONParser jsonParser = new JSONParser();
        String url_login = "http://ec2-54-200-47-19.us-west-2.compute.amazonaws.com/login.php";
        try {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("sjsuid", sjsuid));
            params.add(new BasicNameValuePair("password", password));

            // getting activity details by making HTTP request
            JSONObject json = jsonParser.makeHttpRequest(
                    url_login, "POST", params);

            // check your log for json response
            Log.d("Login", json.toString());

            // json success tag
            success = json.getInt(TAG_SUCCESS);
            if (success == 1) {
                //user info correct
                return 1;
            }
            if (success == 2) {
                //user exists, but password is wrong
                return 2;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static ArrayList<HashMap<String, String>> getAllRatings() {
        // Creating JSON Parser object
        JSONParser jParser = new JSONParser();

        ArrayList<HashMap<String, String>> ratingsList;

        // url to get all posted ratings
        String url_get_ratings = "http://ec2-54-200-47-19.us-west-2.compute.amazonaws.com/get_all_ratings.php";

        ratingsList = new ArrayList<HashMap<String, String>>();

        // activities JSONArray
        JSONArray ratings = null;
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        // getting JSON string from URL
        JSONObject json = jParser.makeHttpRequest(url_get_ratings, "GET", params);

        // Check your log cat for JSON response
        Log.d("Rating: ", json.toString());

        try {
            // Checking for SUCCESS TAG
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // activities found
                // Getting Array of Products
                ratings = json.getJSONArray(TAG_RATINGS);

                // looping through All activities
                for (int i = 0; i < ratings.length(); i++) {
                    JSONObject c = ratings.getJSONObject(i);

                    // Storing each json item in variable
                    String ratingID = c.getString(TAG_RATING_ID);
                    String title = c.getString(TAG_TITLE);
                    String date = c.getString(TAG_DATE);
                    String sjsuid = c.getString(TAG_SJSUID);
                    String maxRate = c.getString(TAG_MAX_RATE);

                    // creating new HashMap
                    HashMap<String, String> map = new HashMap<String, String>();

                    // adding each child node to HashMap key => value
                    map.put(TAG_RATING_ID, ratingID);
                    map.put(TAG_TITLE, title);
                    map.put(TAG_DATE, date);
                    map.put(TAG_SJSUID, sjsuid);
                    map.put(TAG_MAX_RATE, maxRate);

                    // adding HashMap to ArrayList
                    ratingsList.add(map);
                }
                return ratingsList;
            } else {
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean createRating(String title, String maxRate, String sjsuid, String date) {
        JSONParser jsonParser = new JSONParser();
        String url_create_rating = "http://ec2-54-200-47-19.us-west-2.compute.amazonaws.com/create_rating.php";
        //
        //

        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("title", title));
        params.add(new BasicNameValuePair("maxRate", maxRate));
        params.add(new BasicNameValuePair("sjsuid", sjsuid));
        params.add(new BasicNameValuePair("date", date));

        // getting JSON Object
        // Note that create product url accepts POST method
        JSONObject json = jsonParser.makeHttpRequest(url_create_rating,
                "POST", params);

        // check log cat for response
        Log.d("Create Response", json.toString());

        // check for success tag
        try {
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // successfully created product
                return true;

            } else {
                // failed to create product
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean createPoll(String title, String sjsuid, String date) {
        JSONParser jsonParser = new JSONParser();
        String url_create_poll = "http://ec2-54-200-47-19.us-west-2.compute.amazonaws.com/create_poll.php";
        //
        //

        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("title", title));
        params.add(new BasicNameValuePair("sjsuid", sjsuid));
        params.add(new BasicNameValuePair("date", date));

        // getting JSON Object
        // Note that create product url accepts POST method
        JSONObject json = jsonParser.makeHttpRequest(url_create_poll,
                "POST", params);

        // check log cat for response
        Log.d("Create Response", json.toString());

        // check for success tag
        try {
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // successfully created product
                return true;

            } else {
                // failed to create product
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static ArrayList<HashMap<String, String>> getAllPolls() {
        // Creating JSON Parser object
        JSONParser jParser = new JSONParser();

        ArrayList<HashMap<String, String>> pollsList;

        // url to get all posted ratings
        String url_get_polls = "http://ec2-54-200-47-19.us-west-2.compute.amazonaws.com/get_all_polls.php";

        pollsList = new ArrayList<HashMap<String, String>>();

        // activities JSONArray
        JSONArray polls = null;
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        // getting JSON string from URL
        JSONObject json = jParser.makeHttpRequest(url_get_polls, "GET", params);

        // Check your log cat for JSON response
        Log.d("Poll: ", json.toString());

        try {
            // Checking for SUCCESS TAG
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // activities found
                // Getting Array of Products
                polls = json.getJSONArray(TAG_POLLS);

                // looping through All activities
                for (int i = 0; i < polls.length(); i++) {
                    JSONObject c = polls.getJSONObject(i);

                    // Storing each json item in variable
                    String pollID = c.getString(TAG_POLL_ID);
                    String title = c.getString(TAG_TITLE);
                    String date = c.getString(TAG_DATE);
                    String sjsuid = c.getString(TAG_SJSUID);

                    // creating new HashMap
                    HashMap<String, String> map = new HashMap<String, String>();

                    // adding each child node to HashMap key => value
                    map.put(TAG_POLL_ID, pollID);
                    map.put(TAG_TITLE, title);
                    map.put(TAG_DATE, date);
                    map.put(TAG_SJSUID, sjsuid);

                    // adding HashMap to ArrayList
                    pollsList.add(map);
                }
                return pollsList;
            } else {
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}