package com.gia.imagedownloader;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private EditText mUrl;
    private Button button;
    private TextView tv;


   // private static final String TEXT_STATE = "currentText";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUrl = findViewById(R.id.website_edit_text); 
        button = findViewById(R.id.download_button);
        tv = findViewById(R.id.instruction_tv);

    }

    public void downloadImage(View view) {

        String website = mUrl.getText().toString(); //retrieve text for url
        Uri webpage = Uri.parse(website); //create a Uri reference and parse it
        new ImageDownloader(this).execute(String.valueOf(webpage)); //create a ImageDownloader object and execute it
        //Hiding the keyboard
        InputMethodManager ipManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        if(ipManager != null){
            ipManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
        tv.setVisibility(View.INVISIBLE); //Once the picture loads, the textView goes away
        mUrl.setVisibility(View.INVISIBLE); //Once the picture loads, the editText goes away
        button.setVisibility(View.INVISIBLE); //Once the picture loads, the button goes away

    }
}