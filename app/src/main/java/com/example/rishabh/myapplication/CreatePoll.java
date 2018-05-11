package com.example.rishabh.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.rishabh.myapplication.Connect.TAG_POLL_ID;

public class CreatePoll extends AppCompatActivity
{

    private List<EditText> editTextList = new ArrayList<EditText>();

    TextView create_poll_heading;
    TextView create_poll_info;
    Button create_poll_to_menu;

    private EditText row, column;
    private Button submit;
    private LinearLayout main, matrix;

    public int editTextLines = 2;

    EditText poll_title;
    EditText poll_option1;
    EditText poll_option2;

    ArrayList<String> pollOptions;
    Button create_poll_post;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
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
        create_poll_post = (Button) findViewById(R.id.create_poll_post);

        create_poll_post.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String pollID = "";
                String title = poll_title.getText().toString();
                String option1 = poll_option1.getText().toString();
                String option2 = poll_option2.getText().toString();

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

                if (option1.length() != 0) {
                    Connect.createOption(User.get().getID(), pollID, option1);
                }
                if (option2.length() != 0) {
                    Connect.createOption(User.get().getID(), pollID, option2);
                }

                for (EditText editText : editTextList) {
                    if (editText.getText().toString().length() != 0) {
                        Connect.createOption(User.get().getID(), pollID, editText.getText().toString());
                    }

                }


                startActivity(new Intent(CreatePoll.this, UserPolls.class));

            }
        });


        create_poll_to_menu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {


                startActivity(new Intent(CreatePoll.this, Menu.class));
            }
        });

        final Button add_option = (Button) findViewById(R.id.add_option);
        add_option.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Add_Line();
            }
        });

    }

    public void Add_Line()
    {
        LinearLayout ll = (LinearLayout) findViewById(R.id.layoutDec);
        // add edittext
        EditText et = new EditText(this);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        et.setLayoutParams(p);
//        et.setText("");
        editTextLines++;
        et.setHint("Option" + editTextLines);
        et.setId(editTextLines);
        ll.addView(et);
        editTextList.add(et);
    }
}