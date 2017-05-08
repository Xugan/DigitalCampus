package com.foo.digitalcampus;

import android.app.Application;

/**
 * Created by shgl1hz1 on 2017/5/2.
 */

public class MyApplication extends Application {
    private static String username;
    private static String password;
    @Override
    public void onCreate() {
        super.onCreate();

    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        MyApplication.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        MyApplication.password = password;
    }
}
