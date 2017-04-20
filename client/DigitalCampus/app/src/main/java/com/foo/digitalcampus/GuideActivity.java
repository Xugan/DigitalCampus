package com.foo.digitalcampus;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {
    private ViewPager viewpager;
    private GuideViewPagerAdaper adapter;
    private List<ImageView> listpics;
    private Button btnGo;
    private int[] pics = {R.mipmap.college2,R.mipmap.college3,R.mipmap.college4};
    private LinearLayout llContainer;
    private ImageView [] dots ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        viewpager = (ViewPager) findViewById(R.id.viewPager);
        btnGo = (Button) findViewById(R.id.btnGo);
        llContainer = (LinearLayout) findViewById(R.id.llContainer);
        listpics = new ArrayList<>();

        for(int i =0;i<pics.length;i++){
            ImageView iv = new ImageView(this);
            iv.setImageResource(pics[i]);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            listpics.add(iv);
        }

        dots = new ImageView[pics.length];
        for(int i=0;i<dots.length;i++){
            dots[i] = (ImageView) llContainer.getChildAt(i);
            dots[i].setEnabled(true);
            dots[i].setTag(i);
            dots[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewpager.setCurrentItem(Integer.parseInt(v.getTag().toString()));
                }
            });
        }
        dots[0].setEnabled(false);
        adapter = new GuideViewPagerAdaper(listpics);
        viewpager.setAdapter(adapter);
        btnGo.setVisibility(View.GONE);

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 2){
                    btnGo.setVisibility(View.VISIBLE);
                }else{
                    btnGo.setVisibility(View.GONE);
                }

                for(int i=0;i<pics.length;i++){
                    dots[i].setEnabled(true);
                }
                dots[position].setEnabled(false);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void go(View view){
        Intent intent = new Intent(GuideActivity.this,LoginActivity.class);
        startActivity(intent);
        GuideActivity.this.finish();
    }
}
