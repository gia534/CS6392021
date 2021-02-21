package com.gia.menuproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        String msg = getIntent().getStringExtra(MainActivity.EXTRA_SETTING_MSG);
        TextView tv = findViewById(R.id.settings_textview);
        tv.setText(msg);
    }
}
