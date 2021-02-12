package com.gia.scrollingtext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {
    EditText text;
    Button createMessage;
    Button submit;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.editText);
        createMessage = findViewById(R.id.comment_button);
        submit = findViewById(R.id.submit_button);

        text.setVisibility(GONE);
        submit.setVisibility(GONE);
        createMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setVisibility(View.VISIBLE);
                submit.setVisibility(View.VISIBLE);
            }
        });
    }

    public void submit(View view) {
        tv = findViewById(R.id.showComment);
        String message = text.getText().toString();
        tv.setText(message);
        submit.setVisibility(GONE);
        text.setVisibility(GONE);
    }
}