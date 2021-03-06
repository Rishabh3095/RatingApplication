package com.example.rishabh.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
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
import static com.example.rishabh.myapplication.Connect.TAG_RATING_ID;
import static com.example.rishabh.myapplication.Connect.TAG_TITLE;

public class AllRatings extends AppCompatActivity
{

    public static final String TAG_RATING_TITLE = "rating_id";
    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> ratingTitles;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_ratings);
        ratingTitles = new ArrayList<>();

        ListView mListView = (ListView) findViewById(R.id.listview_all_ratings);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ratingTitles);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Intent intent = new Intent(AllRatings.this, RatingActivity.class);
                String ratingID = ((String) adapterView.getItemAtPosition(position)).substring(0, ((String) adapterView.getItemAtPosition(position)).indexOf(" |"));
                intent.putExtra(TAG_RATING_TITLE, ratingID);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        new getRatingDataTask().execute();
    }

    private class getRatingDataTask extends AsyncTask<Void, Void, ArrayList<String>>
    {
        @Override
        protected ArrayList<String> doInBackground(Void... voids)
        {
            ArrayList<String> strings = new ArrayList<>();
            ArrayList<HashMap<String, String>> allRatings = Connect.getAllRatings();
            if (allRatings == null) {
                strings.add("No ratings have been submitted. You should submit one!");
            } else {
                for (HashMap<String, String> hashMap : allRatings) {
                    int ratingRate = Connect.getRatingRate(hashMap.get(TAG_RATING_ID));
                    String formatted = String.format("%s | %s | %10.10s | %s", hashMap.get(TAG_RATING_ID), hashMap.get(TAG_TITLE), hashMap.get(TAG_DATE), ratingRate + "/" + hashMap.get(TAG_MAX_RATE));
                    strings.add(formatted);
                }
            }
            return strings;
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings)
        {
            ratingTitles.clear();
            ratingTitles.addAll(strings);
            mAdapter.notifyDataSetChanged();
        }
    }
}
