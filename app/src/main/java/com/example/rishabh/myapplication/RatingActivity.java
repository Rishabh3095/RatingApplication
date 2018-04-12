package com.example.rishabh.myapplication;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.rishabh.myapplication.AllRatings.TAG_RATING_TITLE;
import static com.example.rishabh.myapplication.Connect.TAG_DATE;
import static com.example.rishabh.myapplication.Connect.TAG_MAX_RATE;
import static com.example.rishabh.myapplication.Connect.TAG_RATING_ID;
import static com.example.rishabh.myapplication.Connect.TAG_RATING_RATE;
import static com.example.rishabh.myapplication.Connect.TAG_TITLE;

public class RatingActivity extends AppCompatActivity
{
    private static final String TAG = "RatingActivity";

    TextView ratingTitleField;
    TextView ratingAverageValue;
    TextView ratingSliderValue;
    Button updateRating;
    HashMap<String, String> ratingData;
    int ratingRate;
    SeekBar ratingSeekBar;
    String ratingID;

    int maxRatingValue;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        ratingTitleField = (TextView) findViewById(R.id.text_view_rating_name);
        ratingAverageValue = (TextView) findViewById(R.id.text_view_average_rating);
        ratingSliderValue = (TextView) findViewById(R.id.text_view_current_rating);
        updateRating = (Button) findViewById(R.id.button_update_rating);


        Bundle extras = getIntent().getExtras();
        ratingID = null;
        if (extras != null) {
            ratingID = extras.getString(TAG_RATING_TITLE);
        } else
            Log.wtf(TAG, "No rating name sent over");


        // Terrible workaround to avoid NetworkOnMainThreadException
        // TODO: move Connect.getRatingRate() to non-UI thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        ratingRate = Connect.getRatingRate(ratingID);
        for (HashMap<String, String> hashMap : Connect.getAllRatings()) {
            if (Integer.parseInt(ratingID) == Integer.parseInt(hashMap.get(TAG_RATING_ID))) {
                ratingData = hashMap;
            }
        }

        if (ratingData != null) {
            maxRatingValue = Integer.parseInt(ratingData.get(TAG_MAX_RATE));
            String ratingTitle = ratingData.get(TAG_TITLE);
            ratingTitleField.setText(ratingTitle);
            ratingAverageValue.setText("Overall rating: " + ratingRate + " /" + maxRatingValue);
        } else Log.wtf(TAG, "Connect.getRatingRate() or Connect.getAllRatings() is returning null");


        ratingSeekBar = (SeekBar) findViewById(R.id.seek_bar_rating);
        ratingSeekBar.setMax(maxRatingValue);
        ratingSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                ratingSliderValue.setText("Your rating: " + progress + "/" + maxRatingValue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });
    }

    // TODO: implement rating vote update
    public void updateRating(View view)
    {
        // Terrible workaround to avoid NetworkOnMainThreadException
        // TODO: move Connect.getRatingRate() to non-UI thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connect.createRatingRate(User.get().getID(),ratingID,Integer.toString(ratingSeekBar.getProgress()));
        finish();
    }
}
