package com.gia.dollarstoeuros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView euroTv;
    private TextView eTv;
    private EditText usDollarsEditText;
    private final double euro = 0.88;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        euroTv = findViewById(R.id.euroAmount_tv);
        usDollarsEditText = findViewById(R.id.dollarAmount_ET);
        eTv = findViewById(R.id.euroTv);
    }

    public void convertToEuro(View view) {
      double dollars = Double.parseDouble(usDollarsEditText.getText().toString());
      euroTv.setText(String.valueOf(dollars * euro));
      eTv.setVisibility(View.VISIBLE);

        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if(inputManager != null){
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}