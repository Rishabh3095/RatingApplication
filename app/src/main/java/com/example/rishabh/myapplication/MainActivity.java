package com.example.rishabh.myapplication;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button logIn;
    Button signUp;
    EditText idField;
    EditText passwordField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logIn = (Button) findViewById(R.id.button_login);
        signUp = (Button) findViewById(R.id.button_sign_up);
        idField = (EditText) findViewById(R.id.text_id_input);
        passwordField = (EditText) findViewById(R.id.text_password_input);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Terrible workaround to avoid NetworkOnMainThreadException
                // TODO: move Connect.getAllRatings() to non-UI thread
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                Connect.login(idField.getText().toString(), passwordField.getText().toString());
                startActivity(new Intent(MainActivity.this, Menu.class));
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignUp.class));
            }
        });
    }
}