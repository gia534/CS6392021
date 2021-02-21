package com.gia.menuproject;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "MESSAGE";
    public static final String EXTRA_MSG= "MESSAGE";
    public static final String EXTRA_SETTING_MSG= "MESSAGE";
    public static final int TEXT_REQUEST = 1;
    public static final int TXT_RQT = 1;
    public static final int TXT_SETTING_RQT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        if(menuItem.getItemId() == R.id.help){
            Intent helpIntent = new Intent(this, HelpActivity.class);
            String help = getText(R.string.helpInfo).toString();
            helpIntent.putExtra(EXTRA_MSG, help);
            startActivityForResult(helpIntent, TXT_RQT);
            return true;
        }else{
            if(menuItem.getItemId() == R.id.settings){
                Intent settingIntent = new Intent(this, SettingsActivity.class);
                String setting = getString(R.string.settings_page);
                settingIntent.putExtra(EXTRA_SETTING_MSG, setting);
                startActivityForResult(settingIntent, TXT_SETTING_RQT);
                return true;
            }
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void message(View msg) {
        String phoneNumber = getText(R.string.phoneNumber).toString();
        String message = getText(R.string.message).toString();
        Intent sms = new Intent(Intent.ACTION_VIEW);
        sms.setData(Uri.parse("smsto:" + phoneNumber));
        sms.putExtra("sms_body", message);
        if(sms.resolveActivity(getPackageManager()) != null){
            startActivity(sms);
        }
    }

    public void navigation(View nav) {
        String location = getText(R.string.location).toString();
        Uri locUri = Uri.parse("geo:0,0?q=" + location);
        Intent naviIntent = new Intent(Intent.ACTION_VIEW, locUri);
        if(naviIntent.resolveActivity(getPackageManager()) != null){
            startActivity(naviIntent);
        }else{
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }

    public void web(View web) {
        String url = getText(R.string.website).toString();
        Uri webpage = Uri.parse(url);
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        if(webIntent.resolveActivity(getPackageManager()) != null){
            startActivity(webIntent);
        }else{
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }

    public void share(View share) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        String chooserTitle = getText(R.string.share_the_love).toString();
        shareIntent.setType("text/plain");
        if(shareIntent.resolveActivity(getPackageManager()) != null){
            startActivity(Intent.createChooser(shareIntent, chooserTitle));
        }else{
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }

    public void create(View crt) {
        Intent launch = new Intent(this, NewActivity.class);
        String greeting = getText(R.string.greeting).toString();
        launch.putExtra(EXTRA_MESSAGE, greeting);
        startActivityForResult(launch, TEXT_REQUEST);
    }

    public void phone(View phn) {
        String pNumber = getText(R.string.phoneNumber).toString();
        Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" +  pNumber));
        if(phoneIntent.resolveActivity(getPackageManager()) != null){
            startActivity(phoneIntent);
        }

    }
}