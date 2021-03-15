package com.gia.studentdatabasehelper;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StudentDataBaseHelper dataBaseHelper = new StudentDataBaseHelper(this);
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();


        //inserting data

        db.execSQL("INSERT INTO student1(name) VALUES ('john')");
        db.execSQL("INSERT INTO student1(name) VALUES ('mary')");
        db.execSQL("INSERT INTO student1(name) VALUES ('isabelle')");
        db.execSQL("INSERT INTO student1(name) VALUES ('maaschous')");



        Cursor c = db.query("student1", null, null,null, null,null, null);
       c.moveToFirst();
       while(!c.isAfterLast()){
            Log.i("Before - SQLiteDemo", c.getString(0) + " " + c.getString(1));
            c.moveToNext();
        }
        c.close();

        Cursor idC;

        String[] idArray = {"id"};
        idC = db.query("student1", idArray, "name= 'john'", null, null, null, null);
        this.startManagingCursor(idC);
        idC.moveToFirst();
        while(!idC.isAfterLast()){
            Log.i("AndroidSQLiteHelperDemo", idC.getString(0));
            idC.moveToNext();
        }
        idC.close();

    }
}