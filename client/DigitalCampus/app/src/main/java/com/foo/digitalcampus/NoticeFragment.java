package com.foo.digitalcampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lirui on 2017/4/1.
 */

public class NoticeFragment extends Fragment implements FoodCallback{
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView lvInfo;
    private List<Food.DataBean.ListBean> list;
    private FoodAdapter adapter;
    private String url = "http://app.lerays.com/api/stream/list?cate_sign=null&cate_list=16&cate_type=cate&pubtime=0";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.notice_fragment,container,false);
        lvInfo = (ListView) view.findViewById(R.id.lvInfo);
        bindData();
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               bindData();
                if(swipeRefreshLayout.isShown()){
                    swipeRefreshLayout.setRefreshing(false);
                }
                Toast.makeText(getContext(), "刷新成功！", Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


        return view;
    }

    @Override
    public void callback(Food food) {
        list.addAll(food.getData().getList());
        adapter.notifyDataSetChanged();
        lvInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(),WebviewActivity.class);
                intent.putExtra("webInfo","http://app.lerays.com/stream/view?"+list.get(position).getQuery_string());
                //Log.i("NoticewebInfo",list.get(position).getQuery_string());
                startActivity(intent);
            }
        });
    }

    private void asyncTask(){
        new FoodAsyncTask(lvInfo,this).execute(url);
    }

    private void bindData(){
        list = new ArrayList<>();
        asyncTask();
        adapter = new FoodAdapter(list,getContext());
        lvInfo.setAdapter(adapter);
    }
}
