package com.example.rishabh.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateRating extends AppCompatActivity {

    TextView createRating;
    EditText createRatingTitle;
    EditText createRatingRange;
    Button Rating_back;
    Button Rating_post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_rating);

        createRating = (TextView) findViewById(R.id.create_rating_heading);
        createRatingTitle = (EditText) findViewById(R.id.create_rating_title);
        createRatingRange = (EditText) findViewById(R.id.create_rating_range);
        Rating_back = (Button) findViewById(R.id.create_rating_go_back);
        Rating_post = (Button) findViewById(R.id.create_rating_post);

        Rating_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateRating.this, Menuuuuu.class));
            }
        });

        Rating_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
