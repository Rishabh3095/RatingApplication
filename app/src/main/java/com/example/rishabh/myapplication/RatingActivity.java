package com.example.rishabh.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.rishabh.myapplication.AllRatings.TAG_RATING_TITLE;

public class RatingActivity extends AppCompatActivity
{
    private static final String TAG = "RatingActivity";

    TextView ratingTitleField;
    TextView ratingAverageValue;
    TextView ratingSliderValue;
    Button updateRating;

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
        String ratingTitle = null;
        if (extras != null) {
            ratingTitle = extras.getString(TAG_RATING_TITLE);
        } else
            Log.wtf(TAG, "No rating name sent over");

        ratingTitleField.setText(ratingTitle);

        // TODO: implement rating data retrieval and display
        SeekBar ratingSeekBar = (SeekBar) findViewById(R.id.seek_bar_rating);
        //ratingSeekBar.setMax(INSERT MAX RATING AMOUNT HERE);
        ratingSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                ratingSliderValue.setText(getString(R.string.your_rating) + progress);
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
    }
}
