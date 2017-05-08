package com.foo.digitalcampus;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Joker-Shen on 2017/4/13.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    private static final int VERSION = 2;
    private static final String DBNAME = "users.db";
    public MySQLiteOpenHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table if not exists report" +
                "(" +
                "_id integer primary key autoincrement," +
                "campus_area text," +
                "case_type text," +
                "image_url text," +
                "content text," +
                "location text," +
                "date text," +
                "department text," +
                "status text"+
                ")");

        db.execSQL("create table if not exists user" +
                "(" +
                "_id integer primary key autoincrement," +
                "username text," +
                "password text" +
                ")");

        db.execSQL("create table if not exists user_info" +
                "(" +
                "_id integer primary key autoincrement," +
                "photo_url text," +
                "name text," +
                "stu_num integer," +
                "depart text," +
                "phone text," +
                "mail text" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion>oldVersion){
            db.execSQL("drop table if exists report");
            db.execSQL("drop table if exists user");
            db.execSQL("drop table if exists user_info");
            onCreate(db);
        }
    }
}
