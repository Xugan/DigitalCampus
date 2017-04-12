package com.foo.digitalcampus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdministratorActivity extends AppCompatActivity {
    private EditText etPassword;
    private static final int PASSWORD = 123;
    private int inputPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator);
        etPassword = (EditText) findViewById(R.id.etPassword);

        Log.i("inputpassword+++++++",inputPassword+"");
    }

    public void login(View view){
       if(TextUtils.isEmpty(etPassword.getText())){
           Toast.makeText(AdministratorActivity.this, "请输入密码！", Toast.LENGTH_SHORT).show();
       }else {
            inputPassword = Integer.parseInt(etPassword.getText().toString());
           if (inputPassword == PASSWORD) {
               Toast.makeText(AdministratorActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(AdministratorActivity.this,CaseActivity.class);
               startActivity(intent);
           } else {
               Toast.makeText(AdministratorActivity.this, "密码错误！", Toast.LENGTH_SHORT).show();
           }
       }
    }
}
