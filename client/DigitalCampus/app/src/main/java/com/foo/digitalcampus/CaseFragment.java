package com.foo.digitalcampus;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class CaseFragment extends Fragment {
    private ListView lvCase_today;
    private CaseTodayAdapter caseTodayAdapter;
    private List<Map<String,Object>> list;
    private SQLiteDatabase db;
    private MySQLiteOpenHelper helper;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.case_fragment,container,false);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);


        lvCase_today = (ListView) view.findViewById(R.id.case_today);

        //bindData();

        lvCase_today.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(),CaseInfoActivity.class);
                intent.putExtra("caseNumber",position);
                startActivity(intent);
            }
        });



        return view;
    }

    private void bindData(){
        list = new ArrayList<>();
        helper = new MySQLiteOpenHelper(getContext());
        db = helper.getReadableDatabase();
        Cursor cursor = db.query("report",null,null,null,null,null,null);
        while(cursor.moveToNext()){
            Map<String,Object> map = new HashMap<>();
            map.put("image_url",cursor.getString(cursor.getColumnIndex("image_url")));
            map.put("case_num",cursor.getInt(cursor.getColumnIndex("_id")));
            map.put("case_type",cursor.getString(cursor.getColumnIndex("case_type")));
            map.put("case_status",cursor.getString(cursor.getColumnIndex("status")));
            map.put("case_date",cursor.getString(cursor.getColumnIndex("date")));
            list.add(map);
        }
        cursor.close();

        caseTodayAdapter = new CaseTodayAdapter(list,getContext());
        lvCase_today.setAdapter(caseTodayAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        bindData();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                bindData();
                Toast.makeText(getContext(),"刷新完成",Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
