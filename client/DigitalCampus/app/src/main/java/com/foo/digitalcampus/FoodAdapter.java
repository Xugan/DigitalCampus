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

/**
 * Created by shgl1hz1 on 2017/5/2.
 */

public class FoodAdapter extends BaseAdapter {
    private List<Food.DataBean.ListBean>list;
    private Context context;

    public FoodAdapter(List<Food.DataBean.ListBean> list, Context context) {
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
        ViewHolder1 holder1 = null;
        ViewHolder2 holder2 = null;
        //Log.i("++++++++",data.getData().getList().size()+"");
        if(convertView == null){
            if(position%5 == 0){
                holder1 = new ViewHolder1();
                convertView = LayoutInflater.from(context).inflate(R.layout.hotdata_listview_layout_one,null);
                holder1.ivPic = (ImageView) convertView.findViewById(R.id.ivPic);
                holder1.tvContent = (TextView) convertView.findViewById(R.id.tvContent);
                convertView.setTag(holder1);
            }else{
                holder2 = new ViewHolder2();
                convertView = LayoutInflater.from(context).inflate(R.layout.hotdata_listview_layout_two,null);
                holder2.ivPic = (ImageView) convertView.findViewById(R.id.ivPic);
                holder2.tvContent = (TextView) convertView.findViewById(R.id.tvContent);
                holder2.tvCate_title = (TextView) convertView.findViewById(R.id.tvCate_title);
                holder2.tvCount = (TextView) convertView.findViewById(R.id.tvCount);
               // holder2.ivPlay = (ImageView) convertView.findViewById(R.id.ivPlay);
                //holder2.ivRightTop = (ImageView) convertView.findViewById(R.id.ivRightTop);
                convertView.setTag(holder2);
            }
        }else{
            if(position%5 == 0){
                holder1 = (ViewHolder1) convertView.getTag();
            }else{
                holder2 = (ViewHolder2) convertView.getTag();
            }
        }

        if(position%5 == 0){
            holder1.tvContent.setText(list.get(position).getTitle());
            String imgStr = list.get(position).getImg_src();
            Picasso.with(context).load(imgStr).into(holder1.ivPic);

        }else{
            holder2.tvContent.setText(list.get(position).getTitle());
            holder2.tvCate_title.setText(list.get(position).getCate_title());
            //holder2.tvCount.setText(data.getData().getList().get(i).getDisplay().getValue());
            int count = list.get(position).getVisit_num();
            if(count>10000){
                holder2.tvCount.setText(count/10000+"万+");
            }else{

                holder2.tvCount.setText(list.get(position).getVisit_num()+"");
            }
            String imgStr = list.get(position).getImg_src();
            Picasso.with(context).load(imgStr).into(holder2.ivPic);
//            if(list.get(position).isHas_video()){
//                holder2.ivPlay.setVisibility(View.VISIBLE);
//            }else{
//                holder2.ivPlay.setVisibility(View.GONE);
//            }
//            holder2.ivRightTop.setVisibility(View.GONE);

//            if(list.get(position).isIs_trending()){
//                holder2.ivRightTop.setVisibility(View.VISIBLE);
//                holder2.ivRightTop.setImageResource(R.mipmap.icon_card_hot);
//            }
//            if(list.get(position).isHas_quiz()){
//                holder2.ivRightTop.setVisibility(View.VISIBLE);
//                holder2.ivRightTop.setImageResource(R.mipmap.icon_card_quiz);
//            }
//            if(list.get(position).isIs_promote()){
//                holder2.ivRightTop.setVisibility(View.VISIBLE);
//                holder2.ivRightTop.setImageResource(R.mipmap.icon_card_intelligent);
//            }

        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        int result = 0;
        if(position%5 == 0){
            result = 0;
        }else{
            result = 1;
        }
        return result;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    private class ViewHolder1{
        private ImageView ivPic;
        private TextView tvContent;
    }
    private class ViewHolder2{
        private ImageView ivPic;
        private TextView tvContent;
        private TextView tvCate_title;
        private TextView tvCount;
        private ImageView ivPlay;
        private ImageView ivRightTop;
    }
}
