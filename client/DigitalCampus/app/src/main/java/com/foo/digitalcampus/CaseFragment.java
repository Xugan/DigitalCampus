package com.foo.digitalcampus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lirui on 2017/4/3.
 */

public class CaseFragment extends Fragment{
    private ListView lvCase_today;
    private CaseTodayAdapter caseTodayAdapter;
    private List<Map<String,String>> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.case_fragment,container,false);
        lvCase_today = (ListView) view.findViewById(R.id.case_today);
        list = new ArrayList<>();
        for(int i=0;i<10;i++){
            Map<String,String> map = new HashMap<>();
            map.put("case_title","第"+(i+1)+"号案卷");
            map.put("case_type","类型");
            map.put("case_time","2017-04-03");
            list.add(map);
        }
        caseTodayAdapter = new CaseTodayAdapter(list,getContext());
        lvCase_today.setAdapter(caseTodayAdapter);
        return view;
    }
}
