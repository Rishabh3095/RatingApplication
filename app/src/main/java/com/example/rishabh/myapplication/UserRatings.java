package com.example.rishabh.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.rishabh.myapplication.Connect.TAG_DATE;
import static com.example.rishabh.myapplication.Connect.TAG_TITLE;

public class UserRatings extends AppCompatActivity {

    TextView userRatingHeading;
    Button userRatingBack;
    Button userRatingAllRatings;
    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> ratingTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_ratings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ratingTitles = new ArrayList<>();

        mListView = (ListView) findViewById(R.id.listview_all_polls);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, updateRatingTitles());
        mListView.setAdapter(mAdapter);


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
    private ArrayList<String> updateRatingTitles() {
        // Terrible workaround to avoid NetworkOnMainThreadException
        // TODO: move Connect.getAllPolls() to non-UI thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ratingTitles.clear();
        for(HashMap<String, String> hashMap : Connect.getUserRatings(User.get().getID()))
        {
            // TODO: retrieve rating and display instead of placeholder text
            String formatted = String.format("%s | %10.10s",hashMap.get(TAG_TITLE), hashMap.get(TAG_DATE));
            ratingTitles.add(formatted);
        }
        return ratingTitles;

    }
}