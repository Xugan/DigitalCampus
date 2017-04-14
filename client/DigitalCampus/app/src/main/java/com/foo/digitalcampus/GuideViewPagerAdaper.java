package com.foo.digitalcampus;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by shgl1hz1 on 2017/4/14.
 */

public class GuideViewPagerAdaper extends PagerAdapter {
    private List<ImageView> listPics;

    public GuideViewPagerAdaper(List<ImageView> listPics) {
        this.listPics = listPics;
    }

    @Override
    public int getCount() {
        return listPics.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(listPics.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(listPics.get(position));
        return listPics.get(position);
    }
}
