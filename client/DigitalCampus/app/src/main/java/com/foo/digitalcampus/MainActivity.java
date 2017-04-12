package com.foo.digitalcampus;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout mainTabLayout;
    ViewPager mainViewPager;
    private LinearLayout llContainer;
    private ImageView ivHome_icon;
    private ImageView ivRepaire_icon;
    private ImageView ivWork_icon;
    private ImageView ivPersonal_icon;
//    private FragmentManager manager;
//    private FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainTabLayout = (TabLayout) findViewById(R.id.main_tab);
        mainViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        llContainer = (LinearLayout) findViewById(R.id.llContainer);
        ivHome_icon = (ImageView) findViewById(R.id.iv_home_icon);
        ivRepaire_icon = (ImageView) findViewById(R.id.iv_repair_icon);
        ivWork_icon = (ImageView) findViewById(R.id.iv_work_icon);
        ivPersonal_icon = (ImageView) findViewById(R.id.iv_personal_icon);

        mainViewPager.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager(),
                MainActivity.this));
        mainTabLayout.setupWithViewPager(mainViewPager);

//        manager = getSupportFragmentManager();
//        transaction = manager.beginTransaction();
//        ivHome_icon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                transaction.replace(R.id.llContiner,new PageFragment());
//                transaction.commit();
//            }
//        });

        ivRepaire_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ReportActivity.class);
                startActivity(intent);
            }
        });
    }
}
