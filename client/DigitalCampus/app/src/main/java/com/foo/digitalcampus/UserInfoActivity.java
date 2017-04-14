package com.foo.digitalcampus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class UserInfoActivity extends AppCompatActivity {
    private TextView tvBack;
    private EditText etUsername;
    private TextView tvEdit;
    private EditText etStuNum;
    private EditText etDepartment;
    private EditText etPhone;
    private EditText etMail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        tvBack = (TextView) findViewById(R.id.tvBack);
        etUsername = (EditText) findViewById(R.id.etUsername);
        tvEdit = (TextView) findViewById(R.id.tvEdit);
        etStuNum = (EditText) findViewById(R.id.etStuNum);
        etDepartment = (EditText) findViewById(R.id.etDepartment);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etMail = (EditText) findViewById(R.id.etMail);
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfoActivity.this.finish();
            }
        });
        etUsername.setEnabled(false);
        etStuNum.setEnabled(false);
        etMail.setEnabled(false);
        etPhone.setEnabled(false);
        etDepartment.setEnabled(false);
        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvEdit.getText().toString().equals("编辑")){
                    tvEdit.setText("保存");
                    etUsername.setEnabled(true);
                    etStuNum.setEnabled(true);
                    etMail.setEnabled(true);
                    etPhone.setEnabled(true);
                    etDepartment.setEnabled(true);
                }else{
                    tvEdit.setText("编辑");
                    etUsername.setEnabled(false);
                    etStuNum.setEnabled(false);
                    etMail.setEnabled(false);
                    etPhone.setEnabled(false);
                    etDepartment.setEnabled(false);

                }
            }
        });
    }
}
