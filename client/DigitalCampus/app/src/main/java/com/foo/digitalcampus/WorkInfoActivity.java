package com.foo.digitalcampus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.reflect.Array;

public class WorkInfoActivity extends AppCompatActivity {
    private Spinner spDepartment;
    private Spinner spStatus;
    private String[] departments = {"后勤部","维修部","水电部"};
    private String[] status = {"驳回","处理中","处理完"};
    private ArrayAdapter<String> departmentAdapter;
    private ArrayAdapter<String> StatusAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_info);
        spDepartment = (Spinner) findViewById(R.id.spDepartment);
        spStatus = (Spinner) findViewById(R.id.spStatus);
        departmentAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,departments);
        StatusAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,status);

        spDepartment.setAdapter(departmentAdapter);
        spStatus.setAdapter(StatusAdapter);
    }
}
