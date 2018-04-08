package com.example.rishabh.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class RatingActivity extends AppCompatActivity
{
    private static final String TAG = "RatingActivity";

    TextView ratingTitle;
    SeekBar ratingSeekBar;
    Button updateRating;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        ratingTitle = (TextView) findViewById(R.id.text_view_rating_name);
        ratingSeekBar = (SeekBar) findViewById(R.id.seek_bar_rating);
        updateRating = (Button) findViewById(R.id.button_update_rating);
        Bundle extras = getIntent().getExtras();
        if(extras == null)
            Log.wtf(TAG, "No rating name sent over");
        // TODO: implement rating data retrieval and display
    }

    // TODO: implement rating vote update
    public void updateRating(View view)
    {
    }
}
