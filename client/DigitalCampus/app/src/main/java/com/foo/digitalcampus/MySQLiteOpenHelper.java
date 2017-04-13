package com.foo.digitalcampus;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shgl1hz1 on 2017/4/13.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DBNAME = "user.db";
    public MySQLiteOpenHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("crate table user if not exist " +
                "(" +
                "_id integer primary key autouincrement," +
                "username text," +
                "stu_num varchar(200)," +
                "department text," +
                "phone_num varchar(200)," +
                "mail text" +
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
