package com.foo.digitalcampus;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.ActionProvider;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText etUserName;
    private EditText etPassword;
    private SQLiteDatabase db;
    private MySQLiteOpenHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUserName = (EditText) findViewById(R.id.et_user_num);
        etPassword = (EditText) findViewById(R.id.et_password);
        helper = new MySQLiteOpenHelper(this);
    }

    public void login(View view){
        db = helper.getReadableDatabase();
        Cursor cursor = db.query("user",null,"username = ?",new String[]{etUserName.getText().toString()},null,null,null);
        while(cursor.moveToNext()){
            String dbPassword = cursor.getString(cursor.getColumnIndex("password"));
            Log.i("dbPassword",dbPassword);
            if(dbPassword.equals(etPassword.getText().toString())){
                Toast.makeText(this, "登录成功！", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this, "密码错误！", Toast.LENGTH_SHORT).show();
            }
        }

        MyApplication myApplication = new MyApplication();
        myApplication.setUsername(etUserName.getText().toString());
        myApplication.setPassword(etPassword.getText().toString());
    }

    public void register(View view){
        db = helper.getReadableDatabase();
        ContentValues values =  new ContentValues();


        if(TextUtils.isEmpty(etUserName.getText().toString())){
            Toast.makeText(this, "请输入学号！", Toast.LENGTH_SHORT).show();
        }

        if(TextUtils.isEmpty(etPassword.getText().toString())){
            Toast.makeText(this, "请输入密码！", Toast.LENGTH_SHORT).show();
        }


        if(!TextUtils.isEmpty(etUserName.getText().toString())&&!TextUtils.isEmpty(etPassword.getText().toString())){
            values.put("username",etUserName.getText().toString());
            values.put("password",etPassword.getText().toString());
            long result = db.insert("user",null, values);
            if(result>0){
                Toast.makeText(this, "恭喜您，注册成功！", Toast.LENGTH_SHORT).show();
            }
        }

    }

}
