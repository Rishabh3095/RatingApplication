package com.example.rishabh.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserRatings extends AppCompatActivity {

    TextView userRatingHeading;
    Button userRatingBack;
    Button userRatingAllRatings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_ratings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        userRatingHeading = (TextView) findViewById(R.id.user_rating_heading);
        userRatingBack = (Button) findViewById(R.id.user_ratings_back);
        userRatingAllRatings = (Button) findViewById(R.id.user_rating_to_all_ratings);

        userRatingBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserRatings.this, Menu.class));
            }
        });

        userRatingAllRatings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserRatings.this, AllRatings.class));
            }
        });
    }
}