package com.gia.whowroteit;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    //Base URL for Books API
    private static final String BOOK_BASE_URL = "https://www.googleapis.com/books/v1/volumes?";
    //Parameter for the search string
    private static final String QUERY_PARAM = "q";
    //Parameter that limits search results.
    private  static final String MAX_RESULTS ="maxResults";
    //Parameter to filter by print type
    private static final String PRINT_TYPE = "printType";
  


    public static String getBookInfo(String queryString){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String bookJSONString = null;

        try{
            Uri  builtURI = Uri.parse(BOOK_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryString)
                    .appendQueryParameter(MAX_RESULTS, "10")
                    .appendQueryParameter(PRINT_TYPE, "books")
                    .build();
            URL requestURL = new URL(builtURI.toString());
            //Open the URL connection and make the request
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            //Set up the response from the URL connection using an InputStream, BufferedReader, and a StringBuilder
            reader = new BufferedReader((new InputStreamReader(urlConnection.getInputStream())));
            //Use a StringBuilder to hold the incoming response
            StringBuilder builder = new StringBuilder();
            //Read input line-by-line into the string while there is still input
            String line;
            while((line = reader.readLine()) != null){
                builder.append(line);
                builder.append("\n");
                /**
                 * The new line does not affect JSON parsing of the response
                 * but it makes it a lot easier to debug the response when
                 * viewed in the log
                 */
                if(builder.length() == 0){
                    return null;
                } else {
                    bookJSONString = builder.toString();
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally{
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.d(LOG_TAG, bookJSONString);

        return bookJSONString;
    }
}
