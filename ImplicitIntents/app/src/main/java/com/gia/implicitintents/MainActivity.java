package com.gia.implicitintents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText mWebsiteEditText;
    private EditText mLocationEditText;
    private EditText mShareTextEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebsiteEditText = findViewById(R.id.website_edittext);
        mLocationEditText = findViewById(R.id.location_edittext);
        mShareTextEditText = findViewById(R.id.share_edittext);
    }

    public void openWebsite(View view) {
        String url = mWebsiteEditText.getText().toString(); //retrieve text for url
        Uri webpage = Uri.parse(url);
        //Specify action and data for that action
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);//Displays the data to the user
        if(intent.resolveActivity(getPackageManager()) != null){ //Return the Activity component that should be used to handle this intent. If multiple activities can handle such, user will be ale to decide
            String chooser = getString(R.string.chooser);
            startActivity(Intent.createChooser(intent, chooser));

        }else{
            Log.d("ImplicitIntents", "Can't handle this!");
        }
    }

    public void openLocation(View view) {
        //get the string value of the location edit text
        String loc = mLocationEditText.getText().toString();
        //parse the string into a Uri Object with a geo search query
        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
        Intent addressIntent = new Intent(Intent.ACTION_VIEW, addressUri);
        //Return activity component
        if(addressIntent.resolveActivity(getPackageManager()) != null){
            startActivity(addressIntent);
        }else{
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }

    public void shareText(View view) {
        String txt = mShareTextEditText.getText().toString();
        //MIME stands for multipurpose internet mail mail extensions
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder.from(this).setType(mimeType).setChooserTitle("Share this text with: ").setText(txt).startChooser();
        //from() is the activity that launches this share Intent. setType() is the mime type of the item to be shared. setChooserTitle is the title that appears on the system app chooser
        //setText() is the actual text to be shared. startChooser shows the system app chooser and sends the Intent
    }
}