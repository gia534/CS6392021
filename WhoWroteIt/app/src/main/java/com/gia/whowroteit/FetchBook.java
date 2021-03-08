package com.gia.whowroteit;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.ref.WeakReference;
/**
 * String because the query is a string.
 * void because no progress indicator,
 * and string because the JSON response is a string
 */
public class FetchBook extends AsyncTask<String, Void, String> {

    private WeakReference<TextView> mTitleText;
    private WeakReference<TextView> mAuthorText;

    /**
     * need weakReference objects for text views to avoid leakage context
     * from the Activity. This will prevent memory leaks by
     * allowing the object held by that reference to be
     * garbage-collected if necessary
     */


    FetchBook(TextView titleText, TextView authorText){
        this.mTitleText = new WeakReference<>(titleText);
        this.mAuthorText = new WeakReference<>(authorText);

    }

    /**
     * getBookInfo() from the NetWorkUtils class
     * is used to make the connection in the backgroun
     *
     */
    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.getBookInfo(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try{
            //Convert the response into a  JSON object
            JSONObject  jsonObject = new JSONObject(s);
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

            //if both are found, display the results.
            if(title != null && authors != null){
                mTitleText.get().setText(title);
                mAuthorText.get().setText(authors);
            } else {
                //if none are found, update the ui to show failed results
                mTitleText.get().setText(R.string.no_results);
                mAuthorText.get().setText(" ");
            }
        }catch (JSONException e){
            //if onPostExecute does not receive a proper JSON string, update the UI to show failed results
            mTitleText.get().setText(R.string.no_results);
            mAuthorText.get().setText(" ");
            e.printStackTrace();
        }

    }
}
