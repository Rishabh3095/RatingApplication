package com.example.rishabh.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    Button createPoll;
    Button createRating;
    Button userPolls;
    Button userRatings;
    Button logOut;
    Button viewAllRatings;
    Button viewAllPolls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        createPoll = (Button) findViewById(R.id.button_create_poll);
        createRating = (Button) findViewById(R.id.button_create_rating);
        userPolls = (Button) findViewById(R.id.button_view_edit_polls);
        userRatings = (Button) findViewById(R.id.button_view_edit_ratings);
        viewAllPolls = (Button) findViewById(R.id.button_view_all_polls);
        viewAllRatings = (Button) findViewById(R.id.button_view_all_ratings);
        logOut = (Button) findViewById(R.id.button_create_poll);

        createPoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menu.this, CreatePoll.class));
            }
        });

        createRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menu.this, CreateRating.class));
            }
        });

        userRatings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        userPolls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        viewAllRatings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        viewAllPolls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}