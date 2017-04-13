package com.ontime.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shgl1hz1 on 2017/4/13.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final String DBNAME = "user.db";
    private static final  int VERSION = 1;
    public MySQLiteHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
