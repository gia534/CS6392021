package com.gia.twoactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.gia.android.twoactivities.extra.REPLY";
    private EditText mReply;
    private static final String LOG_TAG = SecondActivity.class.getSimpleName(); //Returns the simple name of the underlying class as given in the source code.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent(); //gets the intent that activated this activity
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE); //gets the message sent by the mainActivity
        TextView tv = findViewById(R.id.text_message);
        mReply = findViewById(R.id.editText_second);
        tv.setText(message); //this will set the text of the textview to whatever message was received
        Log.d(LOG_TAG, "_______");
        Log.d(LOG_TAG, "onCreate");
    }
    @Override
    public void onStart(){
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    public void returnReply(View view) {
        String reply = mReply.getText().toString();
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, reply);
        setResult(RESULT_OK, replyIntent); //result_ok is a standard activity result. It signifies that the operation succeeded
        Log.d(LOG_TAG, "End SecondActivity");
        finish();//this is called when activity is done and it should be closed

    }
}