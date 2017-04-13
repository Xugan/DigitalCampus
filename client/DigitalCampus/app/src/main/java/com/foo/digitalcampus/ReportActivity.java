package com.foo.digitalcampus;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

public class ReportActivity extends AppCompatActivity {
    private Spinner spSchools;
    private Spinner spCases;
    private String[] schools = {"明伦校区","金明校区","龙子湖校区"};
    private String[] cases = {"校园环境","公共设施","教学设备","办公设备","寝室","园林绿化","紧急突发事件","其他类型"};
    private ArrayAdapter<String> schoolAdapter;
    private ArrayAdapter<String> caseAdapter;
    private ImageButton ibCamara;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        spCases = (Spinner) findViewById(R.id.spCases);
        spSchools = (Spinner) findViewById(R.id.SpSchools);
        schoolAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,schools);
        spSchools.setAdapter(schoolAdapter);
        caseAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,cases);
        spCases.setAdapter(caseAdapter);
        ibCamara = (ImageButton) findViewById(R.id.ibCamara);
        ibCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,10);
            }
        });

    }

}
