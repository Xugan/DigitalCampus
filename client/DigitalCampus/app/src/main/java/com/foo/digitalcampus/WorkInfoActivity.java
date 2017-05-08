package com.foo.digitalcampus;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkInfoActivity extends AppCompatActivity {
    private Spinner spDepartment;
    private Spinner spStatus;
    private String[] departments = {"下拉选择","后勤部","维修部","水电部"};
    private String[] status = {"下拉选择","驳回","处理中","处理完"};
    private ArrayAdapter<String> departmentAdapter;
    private ArrayAdapter<String> StatusAdapter;
    private TextView tvSystemTime;
    private TextView tvCaseNumber;
    private SQLiteDatabase db;
    private MySQLiteOpenHelper helper;
    private String mDeparment;
    private String mStatus;
    private int caseNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_info);

        spDepartment = (Spinner) findViewById(R.id.spDepartment);
        spStatus = (Spinner) findViewById(R.id.spStatus);
        tvSystemTime = (TextView) findViewById(R.id.tvSystemTime);
        tvCaseNumber = (TextView) findViewById(R.id.tvCaseNumber);

        departmentAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,departments);
        StatusAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,status);
        spDepartment.setAdapter(departmentAdapter);
        spStatus.setAdapter(StatusAdapter);

        Intent intent = getIntent();
         caseNumber = intent.getIntExtra("caseNumber",-1);
        Log.i("caseNumber",caseNumber+"");
        tvCaseNumber.setText("第"+(caseNumber+1)+"号案卷");
        //获取系统时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
        Date currentDate = new Date(System.currentTimeMillis());
        String str = format.format(currentDate);
        tvSystemTime.setText(str);
        Log.i("SystemTime",str);

        spDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mDeparment = spDepartment.getSelectedItem().toString();
                Log.i("WorkInfo Departmemt",mDeparment);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mStatus = spStatus.getSelectedItem().toString();
                Log.i("WorkInfo Status",mStatus);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        helper = new MySQLiteOpenHelper(this);

    }

    public void commit(View view){
        db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("department",mDeparment);
        values.put("status",mStatus);
        long result = db.update("report",values,"_id = ?",new String[]{(caseNumber+1)+""});
        if(result>0){
            Toast.makeText(WorkInfoActivity.this, "update success!", Toast.LENGTH_SHORT).show();
        }
        WorkInfoActivity.this.finish();
    }
}
