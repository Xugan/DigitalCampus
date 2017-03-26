package com.foo.digitalcampus;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    TabLayout mainTabLayout;
    ViewPager mainViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainTabLayout = (TabLayout) findViewById(R.id.main_tab);
        mainViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        mainViewPager.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager(),
                MainActivity.this));
        mainTabLayout.setupWithViewPager(mainViewPager);
    }
}
