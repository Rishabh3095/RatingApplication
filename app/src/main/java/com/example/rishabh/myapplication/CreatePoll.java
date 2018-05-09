package com.example.rishabh.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.rishabh.myapplication.Connect.TAG_POLL_ID;

public class CreatePoll extends AppCompatActivity {


    TextView create_poll_heading;
    TextView create_poll_info;
    Button create_poll_to_menu;

    private EditText row, column;
    private Button submit;
    private LinearLayout main, matrix;

    EditText poll_title;
    EditText poll_option1;
    EditText poll_option2;
    EditText poll_option3;
    EditText poll_option4;
    Button create_poll_post;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_poll);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        create_poll_heading = (TextView) findViewById(R.id.create_poll_heading);
        create_poll_info = (TextView) findViewById(R.id.create_poll_info);
        create_poll_to_menu = (Button) findViewById(R.id.create_poll_to_menu);

        poll_title = (EditText) findViewById(R.id.poll_title);
        poll_option1 = (EditText) findViewById(R.id.poll_option1);
        poll_option2 = (EditText) findViewById(R.id.poll_option2);
        poll_option3 = (EditText) findViewById(R.id.poll_option3);
        poll_option4 = (EditText) findViewById(R.id.poll_option4);
        create_poll_post = (Button) findViewById(R.id.create_poll_post);

        create_poll_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pollID = "";
                String title = poll_title.getText().toString();
                String option1 = poll_option1.getText().toString();
                String option2 = poll_option2.getText().toString();
                String option3 = poll_option3.getText().toString();
                String option4 = poll_option4.getText().toString();

                // date will be formatted in yyyy-mm-dd format
                String date = new java.sql.Date(new java.util.Date().getTime()).toString();

                // Terrible workaround to avoid NetworkOnMainThreadException
                // TODO: move Connect.getAllPolls() to non-UI thread
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                Connect.createPoll(title, User.get().getID(), date);
                ArrayList<HashMap<String, String>> allRatings = Connect.getLastPoll();
                    for (HashMap<String, String> hashMap : allRatings) {
                         pollID = hashMap.get(TAG_POLL_ID);
                    }
                if (option1.length() != 0){
                        Connect.createOption(User.get().getID(), pollID, option1);
                }
                if (option2.length() != 0){
                    Connect.createOption(User.get().getID(), pollID, option2);
                }
                if (option3.length() != 0){
                    Connect.createOption(User.get().getID(), pollID, option3);
                }
                if (option4.length() != 0){
                    Connect.createOption(User.get().getID(), pollID, option4);
                }
                startActivity(new Intent(CreatePoll.this, UserPolls.class));

            }
        });


        create_poll_to_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreatePoll.this, Menu.class));
            }
        });

    }
}