package com.foo.digitalcampus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

/**
 * Created by shgl1hz1 on 2017/4/26.
 */

public class CaseActivityAdapter extends BaseAdapter {
    private List<Map<String,Object>> list;
    private Context context;

    public CaseActivityAdapter(List<Map<String, Object>> list, Context context) {
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
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.casetoday_listitem,null);
            holder.ivPic = (ImageView) convertView.findViewById(R.id.iv_case_pic);
            holder.case_time = (TextView) convertView.findViewById(R.id.case_time);
            holder.case_num = (TextView) convertView.findViewById(R.id.case_num);
            holder.case_type = (TextView) convertView.findViewById(R.id.case_type);
            holder.department = (TextView) convertView.findViewById(R.id.department);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
            holder.case_num.setText("第"+list.get(position).get("case_num")+"号案卷");
            holder.case_type.setText(list.get(position).get("case_type")+"");
            holder.case_time.setText(list.get(position).get("date")+"");
            holder.department.setText(list.get(position).get("department")+"");
        Picasso.with(context).load(list.get(position).get("image_url")+"").into(holder.ivPic);
        return convertView;
    }

    class ViewHolder{
        private ImageView ivPic;
        private TextView case_num;
        private TextView case_type;
        private TextView case_time;
        private TextView department;
    }
}
