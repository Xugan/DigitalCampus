package com.foo.digitalcampus;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Joker-Shen on 2017/4/13.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DBNAME = "users.db";
    public MySQLiteOpenHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("crate table if not exists UserInfo " +
                "(" +
                "_id integer primary key autouincrement," +
                "" +
                "username text," +
                "stu_num integer," +
                "department text," +
                "phone_num int," +
                "mail text" +
                ")");
        db.execSQL("create table if not exists Report" +
                "(" +
                "_id integer primary key autoincrement," +
                "casenum integer," +
                "campus-area text," +
                ""+
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion>oldVersion){
            db.execSQL("drop table user");
            onCreate(db);
        }
    }
}
