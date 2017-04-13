package com.foo.digitalcampus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CaseInfoActivity extends AppCompatActivity {
    private TextView tvCaseNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_info);
        tvCaseNumber = (TextView) findViewById(R.id.tvCaseNumber);
        Intent intent = getIntent();
        int caseNumber = intent.getIntExtra("caseNumber",-1);
        tvCaseNumber.setText("第"+(caseNumber+1)+"号案卷");
    }
}
