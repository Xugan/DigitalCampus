package com.foo.digitalcampus;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by lirui on 2017/4/3.
 */

public class AnalysisFragment extends Fragment {
    private SwipeRefreshLayout swipeRefreshLayout;
    private View rootView;
    private PieChart analysisPiechart;
    private String TAG = "AnalysisFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView");
        //return super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.analysis_fragment, container, false);
        analysisPiechart = (PieChart) rootView.findViewById(R.id.analysis_piechart);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getContext(), "刷新成功！", Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
        if (analysisPiechart != null) {
            analysisPiechart.setUsePercentValues(true);
            analysisPiechart.setDrawHoleEnabled(false);
            analysisPiechart.setEntryLabelColor(Color.BLACK);
            analysisPiechart.setEntryLabelTextSize(17f);

            ArrayList<PieEntry> yvalues = new ArrayList<>();
            yvalues.add(new PieEntry(8f, "案件类型A"));
            yvalues.add(new PieEntry(15f, "案件类型B"));
            yvalues.add(new PieEntry(12f, "案件类型C"));
            yvalues.add(new PieEntry(25f, "案件类型D"));
            yvalues.add(new PieEntry(23f, "案件类型E"));
            yvalues.add(new PieEntry(17f, "案件类型F"));
            PieDataSet dataSet = new PieDataSet(yvalues, "Election Results");

            ArrayList<Integer> colors = new ArrayList<Integer>();
            for (int c : ColorTemplate.VORDIPLOM_COLORS)
                colors.add(c);
            for (int c : ColorTemplate.JOYFUL_COLORS)
                colors.add(c);
            for (int c : ColorTemplate.COLORFUL_COLORS)
                colors.add(c);
            for (int c : ColorTemplate.LIBERTY_COLORS)
                colors.add(c);
            for (int c : ColorTemplate.PASTEL_COLORS)
                colors.add(c);
            colors.add(ColorTemplate.getHoloBlue());
            dataSet.setColors(colors);

            ArrayList<String> xVals = new ArrayList<String>();
            xVals.add("January");
            xVals.add("February");
            xVals.add("March");
            xVals.add("April");
            xVals.add("May");
            xVals.add("June");

            PieData data = new PieData(dataSet);
            data.setValueFormatter(new PercentFormatter());
            data.setValueTextSize(18f);
            data.setValueTextColor(Color.BLACK);
            //data.setValueTypeface(mTfLight);
            analysisPiechart.setData(data);
        }
    }
}
