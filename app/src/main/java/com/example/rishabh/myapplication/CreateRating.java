package com.example.rishabh.myapplication;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateRating extends AppCompatActivity
{

    private EditText createRatingTitle;
    private EditText createRatingRange;
    private Button submitRatingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_rating);

        createRatingTitle = (EditText) findViewById(R.id.edit_text_rating_title);
        createRatingRange = (EditText) findViewById(R.id.create_rating_range);
        submitRatingButton = (Button) findViewById(R.id.create_rating_post);

        submitRatingButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String title = createRatingTitle.getText().toString();
                String maxRate = createRatingRange.getText().toString();
                // TODO: get user sjsuid after login is implemented
                String sjsuid;
                // date will be formatted in yyyy-mm-dd format
                String date = new java.sql.Date(new java.util.Date().getTime()).toString();

                // Terrible workaround to avoid NetworkOnMainThreadException
                // TODO: move Connect.getAllRatings() to non-UI thread
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                Connect.createRating(title, maxRate, User.get().getID(), date);
                finish();
            }
        });
    }
}
