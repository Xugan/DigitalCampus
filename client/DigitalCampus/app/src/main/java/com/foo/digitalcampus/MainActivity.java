package com.foo.digitalcampus;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TabLayout mainTabLayout;
    ViewPager mainViewPager;
    //主页面的四个标签
    private ImageView ivHome_icon;
    private ImageView ivRepaire_icon;
    private ImageView ivWork_icon;
    private ImageView ivPersonal_icon;
    private TextView tvHome;
    private TextView tvReport;
    private TextView tvWork;
    private TextView tvService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainTabLayout = (TabLayout) findViewById(R.id.main_tab);
        mainViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        ivHome_icon = (ImageView) findViewById(R.id.iv_home_icon);
        ivRepaire_icon = (ImageView) findViewById(R.id.iv_repair_icon);
        ivWork_icon = (ImageView) findViewById(R.id.iv_work_icon);
        ivPersonal_icon = (ImageView) findViewById(R.id.iv_personal_icon);
        tvHome = (TextView) findViewById(R.id.tv_home);
        tvReport = (TextView) findViewById(R.id.tv_report);
        tvWork = (TextView) findViewById(R.id.tv_work);
        tvService = (TextView) findViewById(R.id.tv_service);
        //viewPager设置适配器
        mainViewPager.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager(),
                MainActivity.this));
        //首页viewPager与上边的Tab标签联动
        mainTabLayout.setupWithViewPager(mainViewPager);


        //点击上报icon跳转首页页面
        ivRepaire_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ReportActivity.class);
                startActivity(intent);
            }
        });
        tvReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ReportActivity.class);
                startActivity(intent);
            }
        });
        //点击工作icon跳转工作页面
        ivWork_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AdministratorActivity.class);
                startActivity(intent);
            }
        });
        tvWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AdministratorActivity.class);
                startActivity(intent);
            }
        });
        //点击服务icon跳转服务页面
        ivPersonal_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ServiceActivity.class);
                startActivity(intent);
            }
        });
        tvService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ServiceActivity.class);
                startActivity(intent);
            }
        });


    }



}
