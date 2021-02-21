package com.gia.menuproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        Intent helpIntent = getIntent(); //gets the intent that activated this activity
        String msg = helpIntent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView tv = findViewById(R.id.text_new_activity);
        tv.setText(msg);
    }
}