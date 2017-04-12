package com.foo.digitalcampus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ReportActivity extends AppCompatActivity {
    private Spinner spSchools;
    private Spinner spCases;
    private String[] schools = {"明伦校区","金明校区","龙子湖校区"};
    private String[] cases = {"校园环境","公共设施","教学设备","办公设备","寝室","园林绿化","紧急突发事件","其他类型"};
    private ArrayAdapter<String> schoolAdapter;
    private ArrayAdapter<String> caseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        spCases = (Spinner) findViewById(R.id.spCases);
        spSchools = (Spinner) findViewById(R.id.SpSchools);
        schoolAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,schools);
        spSchools.setAdapter(schoolAdapter);
        caseAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,cases);
        spCases.setAdapter(caseAdapter);
    }
}
