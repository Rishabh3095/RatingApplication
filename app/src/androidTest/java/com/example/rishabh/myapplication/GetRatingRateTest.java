package com.example.rishabh.myapplication;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.rishabh.myapplication.Connect.TAG_DATE;
import static com.example.rishabh.myapplication.Connect.TAG_MAX_RATE;
import static com.example.rishabh.myapplication.Connect.TAG_RATING_ID;
import static com.example.rishabh.myapplication.Connect.TAG_TITLE;
import static org.junit.Assert.*;

/**
 * Retrieve ratings test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 * Created by emersonsjsu on 4/24/18.
 */
@RunWith(AndroidJUnit4.class)
public class GetRatingRateTest
{
    ArrayList<HashMap<String, String>> allRatings;
    ArrayList<HashMap<String, String>> adminRatings;
    ArrayList<HashMap<String, String>> allPolls;
    ArrayList<HashMap<String, String>> userRatings;
    ArrayList<HashMap<String, String>> userPolls;

    @Test
    public void retrieveAllRatings() throws Exception
    {
        allRatings = Connect.getAllRatings();
        if (allRatings != null) {
            for (HashMap<String, String> hashMap : allRatings) {
                assertEquals(false, hashMap.isEmpty());
            }
        }
    }

    @Test
    public void checkValuesNotNull() throws Exception
    {
        if (allRatings != null) {
            for (HashMap<String, String> hashMap : allRatings) {
                assertNotNull(hashMap.get(TAG_RATING_ID));
                assertNotNull(Connect.getRatingRate(hashMap.get(TAG_RATING_ID)));
                assertNotNull(hashMap.get(TAG_TITLE));
                assertNotNull(hashMap.get(TAG_DATE));
                assertNotNull(hashMap.get(TAG_MAX_RATE));
            }
        }
    }

    @Test
    public void checkRatingWithinRange() throws Exception
    {
        if (allRatings != null) {
            for (HashMap<String, String> hashMap : allRatings) {
                int ratingRate = Connect.getRatingRate(hashMap.get(TAG_RATING_ID));
                int maxRating = Integer.parseInt(hashMap.get(TAG_MAX_RATE));
                assertTrue(ratingRate >= maxRating);
            }
        }
    }

    @Test
    public void retrieveAllAdminRatings() throws Exception
    {
        adminRatings = Connect.getAllRatings();
        if (adminRatings != null) {
            for (HashMap<String, String> hashMap : adminRatings) {
                assertEquals(false, hashMap.isEmpty());
            }
        }
    }

    @Test
    public void retrieveAllPolls() throws Exception
    {
        allPolls = Connect.getAllPolls();
        if (allPolls != null) {
            for (HashMap<String, String> hashMap : allPolls) {
                assertEquals(false, hashMap.isEmpty());
            }
        }
    }

    @Test
    public void checkAdminValuesNotNull() throws Exception
    {
        if (adminRatings != null) {
            for (HashMap<String, String> hashMap : adminRatings) {
                assertNotNull(hashMap.get(TAG_RATING_ID));
                assertNotNull(Connect.getRatingRate(hashMap.get(TAG_RATING_ID)));
                assertNotNull(hashMap.get(TAG_TITLE));
                assertNotNull(hashMap.get(TAG_DATE));
                assertNotNull(hashMap.get(TAG_MAX_RATE));
           }
        }
    }

    @Test
    public void retrieveUserPolls() throws Exception
    {
        userPolls = Connect.getUserPolls(User.get().getID());
        if (userPolls != null) {
            for (HashMap<String, String> hashMap : userPolls) {
                assertEquals(false, hashMap.isEmpty());
            }
        }
    }


    @Test
    public void checkAdminRatingWithinRange() throws Exception
    {
        if (adminRatings != null) {
            for (HashMap<String, String> hashMap : adminRatings) {
                int ratingRate = Connect.getRatingRate(hashMap.get(TAG_RATING_ID));
                int maxRating = Integer.parseInt(hashMap.get(TAG_MAX_RATE));
                assertTrue(ratingRate >= maxRating);
            }
        }
    }

    /*
    User is not defined, these tests will always fail
    @Test
    public void retrieveUserRatings() throws Exception
    {
        userRatings = Connect.getUserRatings(User.get().getID());
        if (userRatings != null) {
            for (HashMap<String, String> hashMap : userRatings) {
                assertEquals(false, hashMap.isEmpty());
            }
        }
    }
    */
}
