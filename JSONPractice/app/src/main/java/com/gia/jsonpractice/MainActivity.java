package com.gia.jsonpractice;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button sortById;
    private Button sortByName;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sortById = findViewById(R.id.sortByIdButton);
        sortByName = findViewById(R.id.sortByNameButton);
        textView = findViewById(R.id.textView);
        dialog = new ProgressDialog(this);
        dialog.show();

        FetchData fetchData = new FetchData();
        fetchData.execute();

    }

    private class FetchData extends AsyncTask<String, Void, String> {
        private String jsonFile = "";
        private String jsonFileParsed = "";
        private List<JSONObject> valuesList;
        private JSONArray jsonArray;
        private JSONObject jsonObject;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            valuesList = new ArrayList<>();

            try {
                String website = "https://fetch-hiring.s3.amazonaws.com/hiring.json";
                URL url = new URL(website);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                //Retrieving Data
                String line = "";
                while(line != null){
                    line = reader.readLine();
                    jsonFile += line;
                }

                jsonArray = new JSONArray(jsonFile);

                for (int i = 0; i < jsonArray.length(); i++){
                    //Object becomes element of JSON array
                    jsonObject = (JSONObject) jsonArray.get(i);

                    if (!jsonObject.isNull("name") && !jsonObject.getString("name").isEmpty()){
                        String parsed = " ID: " + jsonObject.get("id") +
                                ",  List Id: " + jsonObject.get("listId")
                                + ",  Name: " + jsonObject.get("name") + " \n";

                        jsonFileParsed += parsed;

                    }
                }

                Log.i("Fetched Information: ", jsonFileParsed);

            }catch (Exception e){
                Log.e("doInBackground method: ", "Error: " + e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();
            textView.setText(jsonFileParsed);

        }
    }

}