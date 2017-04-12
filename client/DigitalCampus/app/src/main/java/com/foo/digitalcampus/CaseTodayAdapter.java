package com.foo.digitalcampus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by shgl1hz1 on 2017/4/12.
 */

public class CaseTodayAdapter extends BaseAdapter {

    private List<Map<String,String>> list;
    private Context context;

    public CaseTodayAdapter(List<Map<String, String>> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {

        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.casetoday_listitem,null);
            holder = new ViewHolder();
            holder.ivPic = (ImageView) convertView.findViewById(R.id.ivPic);
            holder.case_time = (TextView) convertView.findViewById(R.id.case_time);
            holder.case_title = (TextView) convertView.findViewById(R.id.case_title);
            holder.case_type = (TextView) convertView.findViewById(R.id.case_type);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.ivPic.setImageResource(R.mipmap.notice_item_icon_example);
        holder.case_time.setText(list.get(position).get("case_time"));
        holder.case_type.setText(list.get(position).get("case_type"));
        holder.case_title.setText(list.get(position).get("case_title"));
        return convertView;
    }
    class ViewHolder{
        private ImageView ivPic;
        private TextView case_title;
        private TextView case_type;
        private TextView case_time;
    }
}
