package com.example.rishabh.myapplication;

import android.content.Intent;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.design.widget.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import static com.example.rishabh.myapplication.Connect.TAG_FIRST_NAME;


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
                String id = idField.getText().toString();
                String pass = passwordField.getText().toString();
                int logReturn = Connect.login(id,pass);
                if (logReturn == 0){
                    System.out.println("you shouldnt see this");
                    Snackbar mySnackbar = Snackbar.make(view, "User doesnt exist", 5);
                    //user doesnt exist
                }else if(logReturn == 2){
                    System.out.println("password");
                    //wrong password
                    Snackbar mySnackbar = Snackbar.make(view, "User doesnt exist", 5);
                }else if(logReturn == 1){
                    User.get().setID(id);
                    System.out.println(User.get().getID());
                    System.out.println("good log");
                    ArrayList<HashMap<String, String>> name;

                    if (Connect.getUser(User.get().getID()) != null){
                        name = Connect.getUser(User.get().getID());
                        User.get().setName(name.get(0).get(TAG_FIRST_NAME));
                    }
                    startActivity(new Intent(MainActivity.this, Menu.class));
                }
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