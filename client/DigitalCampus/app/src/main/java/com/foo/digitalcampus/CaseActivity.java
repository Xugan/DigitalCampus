package com.foo.digitalcampus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaseActivity extends AppCompatActivity {
    private ListView lvCase_today;
    private CaseTodayAdapter caseTodayAdapter;
    private List<Map<String,String>> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case);
        lvCase_today = (ListView) findViewById(R.id.case_today);
        list = new ArrayList<>();
        for(int i=0;i<10;i++){
            Map<String,String> map = new HashMap<>();
            map.put("case_title","第"+(i+1)+"号案卷");
            map.put("case_type","类型");
            map.put("case_time","2017-04-03");
            list.add(map);
        }
        caseTodayAdapter = new CaseTodayAdapter(list,this);
        lvCase_today.setAdapter(caseTodayAdapter);

        lvCase_today.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CaseActivity.this,WorkInfoActivity.class);
                intent.putExtra("caseNumber",position);
                Log.i("position",position+"");
                startActivity(intent);
            }
        });
    }
}
