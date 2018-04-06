package com.example.rishabh.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Menuuuuu extends AppCompatActivity {

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
                startActivity(new Intent(Menuuuuu.this, CreatePoll.class));
            }
        });

        createRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menuuuuu.this, CreateRating.class));
            }
        });

        userRatings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menuuuuu.this, UserRatings.class));
            }
        });

        userPolls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menuuuuu.this, UserPolls.class));
            }
        });

        viewAllRatings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menuuuuu.this, AllRatings.class));
            }
        });

        viewAllPolls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menuuuuu.this, AllPolls.class));
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}