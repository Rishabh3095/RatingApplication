package com.example.rishabh.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.StrictMode;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.rishabh.myapplication.Connect.TAG_DATE;
import static com.example.rishabh.myapplication.Connect.TAG_TITLE;

public class AllPolls extends AppCompatActivity {

    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> pollTitles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_polls);
        pollTitles = new ArrayList<>();

        mListView = (ListView) findViewById(R.id.listview_all_polls);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, updatePollTitles());
        mListView.setAdapter(mAdapter);
    }

    private ArrayList<String> updatePollTitles() {
        // Terrible workaround to avoid NetworkOnMainThreadException
        // TODO: move Connect.getAllPolls() to non-UI thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        pollTitles.clear();
        for(HashMap<String, String> hashMap : Connect.getAllPolls())
        {
            // TODO: retrieve rating and display instead of placeholder text
            String formatted = String.format("%s | %10.10s",hashMap.get(TAG_TITLE), hashMap.get(TAG_DATE));
            pollTitles.add(formatted);
        }
        return pollTitles;
    }
}
