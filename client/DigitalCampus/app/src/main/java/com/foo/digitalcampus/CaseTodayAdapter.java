package com.foo.digitalcampus;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by shgl1hz1 on 2017/4/12.
 */

public class CaseTodayAdapter extends BaseAdapter {

    private List<Map<String,Object>> list;
    private Context context;

    public CaseTodayAdapter(List<Map<String, Object>> list, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_casetoday_listitem,null);
            holder = new ViewHolder();
            holder.ivPic = (ImageView) convertView.findViewById(R.id.ivPic);
            holder.case_time = (TextView) convertView.findViewById(R.id.case_time);
            holder.case_num = (TextView) convertView.findViewById(R.id.case_num);
            holder.case_type = (TextView) convertView.findViewById(R.id.case_type);
            holder.case_status = (TextView) convertView.findViewById(R.id.case_status);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(context).load(new File(list.get(position).get("image_url")+"")).into(holder.ivPic);
//        Bitmap bitmap = BitmapFactory.decodeFile(list.get(position).get("image_url")+"");
//        holder.ivPic.setImageBitmap(bitmap);
        holder.case_time.setText(list.get(position).get("case_date")+"");
        holder.case_type.setText(list.get(position).get("case_type")+"");
        holder.case_num.setText("第"+list.get(position).get("case_num")+"号案卷");
        holder.case_status.setText(list.get(position).get("case_status")+"");
        return convertView;
    }
    class ViewHolder{
        private ImageView ivPic;
        private TextView case_num;
        private TextView case_type;
        private TextView case_time;
        private TextView case_status;
    }
}
