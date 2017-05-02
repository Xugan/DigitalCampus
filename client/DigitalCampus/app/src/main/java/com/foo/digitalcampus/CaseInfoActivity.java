package com.foo.digitalcampus;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaseInfoActivity extends AppCompatActivity {
    private TextView tvCaseNumber;
    private TextView tvCampusArea;
    private TextView tvCaseType;
    private ImageView ivCasePic;
    private TextView tvLocation;
    private TextView tvCaseStatus;
    private TextView tvCaseDate;
    private TextView tvContent;
    private MySQLiteOpenHelper helper;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_info);
        tvCaseNumber = (TextView) findViewById(R.id.tv_case_num);
        tvCampusArea = (TextView) findViewById(R.id.tv_campus_area);
        tvCaseType = (TextView) findViewById(R.id.tv_case_type);
        tvLocation = (TextView) findViewById(R.id.tv_location);
        tvCaseStatus = (TextView) findViewById(R.id.tv_status);
        ivCasePic = (ImageView) findViewById(R.id.iv_case_pic);
        tvCaseDate = (TextView) findViewById(R.id.tv_case_time);
        tvContent = (TextView) findViewById(R.id.tv_content);
        //从intent中取出案件编号
        Intent intent = getIntent();
        int caseNumber = intent.getIntExtra("caseNumber",-1);
        //绑定案件编号
       // tvCaseNumber.setText("第"+(caseNumber+1)+"号案卷");
        helper = new MySQLiteOpenHelper(this);
        db = helper.getReadableDatabase();

        //Cursor cursor = db.query("report",null,null,null,null,null,null);
        Cursor cursor = db.query("report",null,"_id = ?",new String[]{((caseNumber+1)+"")},null,null,null);
        while(cursor.moveToNext()){
            tvContent.setText(cursor.getString(cursor.getColumnIndex("content")));
            tvCaseNumber.setText("第"+cursor.getInt(cursor.getColumnIndex("_id"))+"号案卷");
            tvCaseDate.setText(cursor.getString(cursor.getColumnIndex("date")));
            tvCampusArea.setText(cursor.getString(cursor.getColumnIndex("campus_area")));
            tvCaseType.setText(cursor.getString(cursor.getColumnIndex("case_type")));
            tvLocation.setText(cursor.getString(cursor.getColumnIndex("location")));
            tvCaseStatus.setText(cursor.getString(cursor.getColumnIndex("status")));
            Picasso.with(this).load(cursor.getString(cursor.getColumnIndex("image_url"))).into(ivCasePic);
        }
       cursor.close();

    }
}
