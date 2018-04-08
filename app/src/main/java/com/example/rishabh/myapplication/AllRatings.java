package com.example.rishabh.myapplication;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.rishabh.myapplication.Connect.TAG_DATE;
import static com.example.rishabh.myapplication.Connect.TAG_MAX_RATE;
import static com.example.rishabh.myapplication.Connect.TAG_TITLE;

public class AllRatings extends AppCompatActivity {

    public static final String TAG_RATING_TITLE = "rating_id";
    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> ratingTitles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_ratings);
        ratingTitles = new ArrayList<>();

        mListView = (ListView) findViewById(R.id.listview_all_ratings);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, updateRatingTitles());
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Intent intent = new Intent(AllRatings.this, RatingActivity.class);
                intent.putExtra(TAG_RATING_TITLE, (String) adapterView.getSelectedItem());
                startActivity(intent);
            }
        });
    }

    private ArrayList<String> updateRatingTitles() {
        // Terrible workaround to avoid NetworkOnMainThreadException
        // TODO: move Connect.getAllRatings() to non-UI thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ratingTitles.clear();
        for(HashMap<String, String> hashMap : Connect.getAllRatings())
        {
            // TODO: retrieve rating and display instead of placeholder text
            String formatted = String.format("%s | %10.10s | %s",hashMap.get(TAG_TITLE), hashMap.get(TAG_DATE), "RATING/" + hashMap.get(TAG_MAX_RATE));
            ratingTitles.add(formatted);
        }
        return ratingTitles;
    }
}
