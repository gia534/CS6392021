package com.gia.studentdatabasehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class StudentDataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "student1.db";
    private static final int DATABASE_VERSION = 1;
    public StudentDataBaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE student1(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /**
         * this database is only a cache for online data,
         * so its upgrade policy is to simply discard the
         * data and start over
         */
        /**
         * db.execSQL(SQL_DELETE_ENTRIES);
         * onCreate(db);
         */

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        //onUpgrade(db, oldVersion, newVersion);
    }
}
