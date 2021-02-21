package com.gia.menuproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Intent intent = getIntent(); //gets the intent that activated this activity
        String msg = intent.getStringExtra(MainActivity.EXTRA_MSG);
        TextView tv = findViewById(R.id.help_textview);
        tv.setText(msg);
    }
}