package com.gia.hellosharedprefs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


/**
 * HelloSharedPrefs is an adaptation of the HelloToast app from chapter 1.
 * It includes:
 * - Buttons for changing the background color.
 * - Maintenance of instance state.
 * - Themes and styles.
 * - Read and write shared preferences for the current count and the color.
 * <p>
 * This is the starter code for HelloSharedPrefs.
 */
public class MainActivity extends AppCompatActivity {
    // Current count
    private int mCount = 0;
    // Current background color
    private int mColor;
    // Text view to display both count and color
    private TextView mShowCountTextView;

    // Key for current count
    private final String COUNT_KEY = "count"; //this is used to save the current state of mCount
    // Key for current color
    private final String COLOR_KEY = "color"; //this is used to save the current state of mColor

    //Shared Preferences File
    private SharedPreferences mPreferences;
    //reference to a SharedPreferences object;
    private String sharePrefFile = "com.gia.android.hellosharedprefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views, color
        mShowCountTextView = findViewById(R.id.count_textview);
        mColor = ContextCompat.getColor(this,
                R.color.grey);
        /**
         * The getSharedPreferences() method opens the file at
         * the given filename, sharedPrefFile, with a private mode
         */
        mPreferences = getSharedPreferences(sharePrefFile, MODE_PRIVATE);

        /**
         * when data is read from the preferences, retrieving a shared preferences
         * editor is not needed. Instead, use any of the "get" methods on a shared
         * preferences object to retrieve preference data. (getInt() takes 2 arguments
         * one for the key, and the other for the default value, if the key cannot be found.
         * Default value in this case is 0)
         */
        mCount = mPreferences.getInt(COUNT_KEY, 0);
        mShowCountTextView.setText(String.format("%s", mCount)); //updating value of main TextView with new count
        mColor = mPreferences.getInt(COLOR_KEY, mColor);
        mShowCountTextView.setBackgroundColor(mColor);

    }

    /**
     * Handles the onClick for the background color buttons. Gets background
     * color of the button that was clicked, and sets the TextView background
     * to that color.
     *
     * @param view The view (Button) that was clicked.
     */
    public void changeBackground(View view) {
        int color = ((ColorDrawable) view.getBackground()).getColor();
        mShowCountTextView.setBackgroundColor(color);
        mColor = color;
        /**
         * this onClick handle gets the background color of the button
         * that was clicked. So if the black button  was clicked, its gets the
         * black background and sets that as the background of the textview
         */

    }

    /**
     * Handles the onClick for the Count button. Increments the value of the
     * mCount global and updates the TextView.
     *
     * @param view The view (Button) that was clicked.
     */
    public void countUp(View view) {
        mCount++;
        mShowCountTextView.setText(String.format("%s", mCount));
    }


    /**
     * Handles the onClick for the Reset button. Resets the global count and
     * background variables to the defaults and resets the views to those
     * default values.
     *
     * @param view The view (Button) that was clicked.
     */
    public void reset(View view) {
        // Reset count
        mCount = 0;
        mShowCountTextView.setText(String.format("%s", mCount));

        //Reset Color
        mColor = ContextCompat.getColor(this, R.color.grey);
        mShowCountTextView.setBackgroundColor(mColor);
        //Clear preferences
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.clear();
        preferencesEditor.apply();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        // an editor is required to write to the shared preferences object
        preferencesEditor.putInt(COUNT_KEY, mCount);
        preferencesEditor.putInt(COLOR_KEY, mColor);
        preferencesEditor.apply();
        /**
         * the apply method saves the preferences asynchronously, off of the UI thread
         * There is also a commit() that saves the preferences synchronously, this
         * method is highly discouraged.
         */

    }
}