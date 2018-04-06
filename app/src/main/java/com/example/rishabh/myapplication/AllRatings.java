package com.example.rishabh.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class AllRatings extends AppCompatActivity {

    private ListView mListView;
    private ArrayAdapter<HashMap<String, String>> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_ratings);


        mListView = (ListView) findViewById(R.id.listview_all_ratings);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Connect.getAllRatings());
        mListView.setAdapter(mAdapter);
    }


}
