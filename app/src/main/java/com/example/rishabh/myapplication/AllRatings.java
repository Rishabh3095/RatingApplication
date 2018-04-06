package com.example.rishabh.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class AllRatings extends AppCompatActivity {

    private ProgressDialog pDialog;

    ArrayList<HashMap<String, String>> ratingsList;
    private static final String TAG_NAME = "name";

    JSONArray activities = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_ratings);
        new LoadAllActivities().execute();

    }

    class LoadAllActivities extends AsyncTask<String, String, String> {
        protected String doInBackground(String... args) {
            Intent i = getIntent();

            // getting activity id (id) from intent
            String name = i.getStringExtra(TAG_NAME);

            ratingsList=Connect.getAllRatings();
            if(ratingsList==null){
                Intent in = new Intent(getApplicationContext(),
                        Menu.class);
                //Closing all previous activities
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);
            }
            else
            {
                
            }
            return null;
        }
    }}