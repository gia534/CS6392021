package com.gia.whowroteit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private EditText mBookInput;
    private TextView mTitleText;
    private TextView mAuthorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBookInput = findViewById(R.id.bookInput);
        mTitleText = findViewById(R.id.titleText);
        mAuthorText = findViewById(R.id.authorText);

        //reconnects to loader, if the loader already exists
        if (getSupportLoaderManager().getLoader(0) !=null){
            getSupportLoaderManager().initLoader(0, null, this);

        }
    }

    public void searchBooks(View view) {
        //Get search string from input field.
        String queryString = mBookInput.getText().toString();

        //Hiding the keyboard when inputManager is not empty
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        if(inputManager != null){
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }

        //Checking the network connection
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;


        if(connMgr != null){
            networkInfo = connMgr.getActiveNetworkInfo();
        }
        //Changes the title TextView to a loading message and clear the author TextView
        if(networkInfo != null && networkInfo.isConnected() && queryString.length() != 0) {
            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryString", queryString);
            getSupportLoaderManager().restartLoader(0, queryBundle, this);

            mAuthorText.setText("");
            mTitleText.setText(R.string.loading);
        } else{
            if(queryString.length() == 0){
                mAuthorText.setText("");
                mTitleText.setText(R.string.no_search_term);
            }else{
                mAuthorText.setText("");
                mTitleText.setText(R.string.no_network);
            }
        }
    }

    @NonNull
    @Override
    //This is called when you instantiate your loader
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String queryString = "";
        if(args != null){
            queryString = args.getString("queryString");
        }
        return new BookLoader(this, queryString);
    }

    @Override
    //This is called when the loader's task finishes
    //This is where you add the code to update your UI with the results
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try{
            //Convert the response into a  JSON object
            JSONObject jsonObject = new JSONObject(data);
            //Get  the JSONArray of book items
            JSONArray itemsArray = jsonObject.getJSONArray("items");

            int i = 0;
            String title = null;
            String authors = null;
            /**
             * Look for results in the items array, exiting
             * when both the title and author are found
             */
            while(i < itemsArray.length() && (authors == null && title == null)){
                //get  the current item information
                JSONObject book = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");

                /**
                 * Try to get the author and title from
                 * the current item, catch if either field is empty and move on
                 */

                try{
                    title = volumeInfo.getString("title");
                    authors = volumeInfo.getString("authors");
                } catch(Exception e){
                    e.printStackTrace();
                }

                //move on to the next item.
                i++;
            }
            if(title != null && authors != null){
                mTitleText.setText(title);
                mAuthorText.setText(authors);
                //mBookInput.setText("");
            }else{
                //if non are found, update the UI to show failed results
                mTitleText.setText(R.string.no_results);
                mAuthorText.setText("");
            }
        }catch (JSONException e){
            //if onPostExecute does not receive a proper JSON string, update the UI to show failed results
            mTitleText.setText(R.string.no_results);
            mAuthorText.setText("");
            e.printStackTrace();
            e.printStackTrace();
        }
    }

    @Override
    ////this cleans up any remaining resources
    public void onLoaderReset(@NonNull  Loader<String> loader) {

    }
}