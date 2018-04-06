package com.example.rishabh.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUp extends AppCompatActivity {

    Button enroll;
    TextView heading;
    TextView info;
    EditText firstName;
    EditText lastName;
    EditText sjsuId;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        enroll = (Button) findViewById(R.id.button_enroll_signup);
        heading = (TextView) findViewById(R.id.sign_up_heading);
        info = (TextView) findViewById(R.id.textx_signUp);
        firstName = (EditText) findViewById(R.id.first_name_signUp);
        lastName = (EditText) findViewById(R.id.last_name_signUp);
        sjsuId = (EditText) findViewById(R.id.sjsuId_signUp);
        password = (EditText) findViewById(R.id.password_signUp);

        enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
