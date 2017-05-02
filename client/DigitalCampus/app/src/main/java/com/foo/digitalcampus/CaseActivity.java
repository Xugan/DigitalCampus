package com.foo.digitalcampus;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    private List<Map<String,Object>> list;
    private SQLiteDatabase db;
    private MySQLiteOpenHelper helper;
    private CaseActivityAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case);

        lvCase_today = (ListView) findViewById(R.id.case_today);

        helper = new MySQLiteOpenHelper(this);
        db = helper.getReadableDatabase();
        list = new ArrayList<>();
        Cursor cursor = db.query("report",null,null,null,null,null,null);
        while(cursor.moveToNext()){
            Map<String,Object> map = new HashMap<>();
            map.put("case_num",cursor.getInt(cursor.getColumnIndex("_id")));
            map.put("case_type",cursor.getString(cursor.getColumnIndex("case_type")));
            map.put("department",cursor.getString(cursor.getColumnIndex("department")));
            map.put("date",cursor.getString(cursor.getColumnIndex("date")));
            map.put("image_url",cursor.getString(cursor.getColumnIndex("image_url")));
            list.add(map);
        }
        cursor.close();
//        for(int i=0;i<10;i++){
//            Map<String,String> map = new HashMap<>();
//            map.put("case_title","第"+(i+1)+"号案卷");
//            map.put("case_type","类型");
//            map.put("case_time","2017-04-03");
//            list.add(map);
//        }
        //适配器
        adapter = new CaseActivityAdapter(list,this);

        lvCase_today.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        //监听listview的子项点击事件
        lvCase_today.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //点击子项跳转至工作详情页面
                Intent intent = new Intent(CaseActivity.this,WorkInfoActivity.class);
                //将案件编号传至工作详情页面
                intent.putExtra("caseNumber",position);
                Log.i("position",position+"");
                startActivity(intent);
            }
        });
    }
}
