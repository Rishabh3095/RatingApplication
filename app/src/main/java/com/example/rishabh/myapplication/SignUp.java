package com.example.rishabh.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.view.MotionEvent;
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
    String evalve;

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

        sjsuId.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                evalve = "1";
                return false;
            }
        });

        enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(sjsuId.length() < 9)
                {
                    Snackbar mySnackbar = Snackbar.make(view, "Please fill in correct SJSU ID!", 5000);
                    mySnackbar.show();
                }
                else if(firstName.length() == 0)
                {
                    Snackbar mySnackbar = Snackbar.make(view, "Please fill in the information!", 5000);
                    mySnackbar.show();
                }
                else if(lastName.length() == 0)
                {
                    Snackbar mySnackbar = Snackbar.make(view, "Please fill in the information!", 5000);
                    mySnackbar.show();
                }
                else if(password.length() < 6)
                {
                    Snackbar mySnackbar = Snackbar.make(view, "Your password is too short!", 5000);
                    mySnackbar.show();
                }
                else
                {
                    // Terrible workaround to avoid NetworkOnMainThreadException
                    // TODO: move Connect.getAllRatings() to non-UI thread
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    Connect.newUser(sjsuId.getText().toString(),lastName.getText().toString(),firstName.getText().toString(),password.getText().toString());
                    startActivity(new Intent(SignUp.this, MainActivity.class));
                }
            }
        });
    }

}
