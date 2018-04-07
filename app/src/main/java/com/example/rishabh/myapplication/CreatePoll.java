package com.example.rishabh.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

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
        setContentView(R.layout.activity_create_poll_2);
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