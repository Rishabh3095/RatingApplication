package com.example.rishabh.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.rishabh.myapplication.Connect.TAG_DATE;
import static com.example.rishabh.myapplication.Connect.TAG_POLL_ID;
import static com.example.rishabh.myapplication.Connect.TAG_TITLE;

public class AllPolls extends AppCompatActivity {

    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> pollTitles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_polls);
        ListView mListView = (ListView) findViewById(R.id.listview_all_polls);

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pollTitles);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Intent intent = new Intent(AllPolls.this, PollActivity.class);
                String pollID = ((String) adapterView.getItemAtPosition(position)).substring(0, ((String) adapterView.getItemAtPosition(position)).indexOf(" |"));
                intent.putExtra(TAG_POLL_ID, pollID);
                intent.putExtra(TAG_TITLE,pollTitles.get(position));
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
            ArrayList<HashMap<String, String>> allPolls = Connect.getAllPolls();
            if (allPolls == null) {
                strings.add("No polls have been submitted. You should submit one!");
            } else {
                for (HashMap<String, String> hashMap : allPolls) {
                    String formatted = String.format("%s | %s | %10.10s", hashMap.get(TAG_POLL_ID), hashMap.get(TAG_TITLE), hashMap.get(TAG_DATE));
                    strings.add(formatted);
                }
            }
            return strings;
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings)
        {
            pollTitles.clear();
            pollTitles.addAll(strings);
            mAdapter.notifyDataSetChanged();
        }
    }

}
