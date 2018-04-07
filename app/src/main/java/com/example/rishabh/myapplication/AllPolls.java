package com.example.rishabh.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.StrictMode;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.rishabh.myapplication.Connect.TAG_TITLE;

public class AllPolls extends AppCompatActivity {

    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> ratingTitles;
/*  TODO: Implement php script to retrieve all polls
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_polls);
        ratingTitles = new ArrayList<>();

        mListView = (ListView) findViewById(R.id.listview_all_polls);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, updateRatingPolls());
        mListView.setAdapter(mAdapter);
    }

    private ArrayList<String> updateRatingPolls() {
        // Terrible workaround to avoid NetworkOnMainThreadException
        // TODO: move Connect.getAllRatings() to non-UI thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ratingTitles.clear();
        for(HashMap<String, String> hashMap : Connect.getAllPolls)
        {
            ratingTitles.add(hashMap.get(TAG_TITLE));
        }
        return ratingTitles;
    }
*/
}
