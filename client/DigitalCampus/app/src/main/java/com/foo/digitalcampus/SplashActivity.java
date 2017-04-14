package com.foo.digitalcampus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private SharedPreferences sp;
    private boolean isFirst = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
        sp = getSharedPreferences("firstUse",MODE_PRIVATE);
        isFirst = sp.getBoolean("firstKey",true);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent();
                if(isFirst){
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("firstKey",false);
                    editor.commit();
                    intent.setClass(SplashActivity.this,GuideActivity.class);

                }else{
                    intent.setClass(SplashActivity.this,MainActivity.class);
                }

                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("firstKey",true);
                editor.commit();
                startActivity(intent);
                SplashActivity.this.finish();
            }
        },3000);

    }
}
