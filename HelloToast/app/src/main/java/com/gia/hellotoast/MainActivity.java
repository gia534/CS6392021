package com.gia.hellotoast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int mCount = 0;
    private TextView mShowCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = findViewById(R.id.show_count);

    }
    public void showToast(View view) {
        //A toast is a view containing a quick little message for the user.
        //It contains the context or the activity where the message will show, the message, and how long it'll last in this case 2 seconds
        Toast toast = Toast.makeText(this, R.string.toast_message,Toast.LENGTH_SHORT);
        toast.show();
    }
    public void countUp(View view) {
        //showText method is used so the value of mCount can be seen
        mCount++;
        if(mShowCount != null){
            mShowCount.setText(String.valueOf(mCount));
            //reason we use toString is because countUp is a textview
        }
    }
    //Restarts Counter
    public void snooze(View v) {
        //Set mCount to 0 then use setText to show mCount
        mCount = 0;
        mShowCount.setText(String.valueOf(mCount));
    }
}