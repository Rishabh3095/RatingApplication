package com.example.rishabh.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.rishabh.myapplication.Connect.TAG_DATE;
import static com.example.rishabh.myapplication.Connect.TAG_POLL_ID;
import static com.example.rishabh.myapplication.Connect.TAG_TITLE;
import static com.example.rishabh.myapplication.Connect.TAG_VOTES;

public class PollActivity extends AppCompatActivity
{
    private static final String TAG = "PollActivity";
    ListView optionsListView;
    ArrayList<String> optionStrings = new ArrayList<>();
    ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll);

        TextView pollTitle = (TextView) findViewById(R.id.text_view_poll_name);
        // Get title from display string by extracting value between '|'
        String displayString = getIntent().getExtras().get(TAG_TITLE).toString();
        displayString = displayString.substring(displayString.indexOf("|")+1);
        displayString = displayString.substring(0,displayString.indexOf("|")-1);
        pollTitle.setText(displayString);

        optionsListView = (ListView) findViewById(R.id.option_list);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, optionStrings);
        optionsListView.setAdapter(mAdapter);
        String pollId = (String) getIntent().getExtras().get(TAG_POLL_ID);
        new getOptionsFromDb().execute(pollId);
    }

    private class getOptionsFromDb extends AsyncTask<String, Void, ArrayList<String>>
    {
        @Override
        protected ArrayList<String> doInBackground(String... params)
        {
            String pollId = params[0];
            ArrayList<String> stringsToDisplay = new ArrayList<>();
            ArrayList<HashMap<String, String>> allOptions = Connect.getPollOptions(pollId);

            if (allOptions == null) {
                Log.wtf(TAG, "Poll has no options");
            } else {
                for (HashMap<String, String> hashMap : allOptions) {
                    String formatted = String.format("%s | %s", hashMap.get(TAG_VOTES), hashMap.get(TAG_TITLE));
                    stringsToDisplay.add(formatted);
                }
            }
            return stringsToDisplay;
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings)
        {
            optionStrings.clear();
            optionStrings.addAll(strings);
            mAdapter.notifyDataSetChanged();
        }
    }

}
