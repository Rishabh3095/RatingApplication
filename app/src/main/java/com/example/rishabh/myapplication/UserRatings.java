package com.example.rishabh.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.rishabh.myapplication.Connect.TAG_DATE;
import static com.example.rishabh.myapplication.Connect.TAG_MAX_RATE;
import static com.example.rishabh.myapplication.Connect.TAG_RATING_ID;
import static com.example.rishabh.myapplication.Connect.TAG_TITLE;

public class UserRatings extends AppCompatActivity
{

    public static final String TAG_RATING_TITLE = "rating_id";
    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> ratingTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_ratings);
        ratingTitles = new ArrayList<>();

        ListView mListView = (ListView) findViewById(R.id.listview_user_ratings);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ratingTitles);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Intent intent = new Intent(UserRatings.this, RatingActivity.class);
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
            ArrayList<HashMap<String, String>> userRatings = Connect.getUserRatings(User.get().getID());
            if (userRatings == null) {
                strings.add("You have not submitted any ratings. You should submit one!");
            } else {
                for (HashMap<String, String> hashMap : userRatings) {
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