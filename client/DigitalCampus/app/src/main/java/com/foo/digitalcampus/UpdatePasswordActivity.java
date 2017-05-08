package com.foo.digitalcampus;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdatePasswordActivity extends AppCompatActivity {
    private EditText etOriPassword;
    private EditText etNewPassword;
    private EditText etConfirmPassword;
    private MySQLiteOpenHelper helper;
    private SQLiteDatabase db;
    private String loginUsername;
    private String loginPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        etOriPassword = (EditText) findViewById(R.id.etOriPassword);
        etNewPassword = (EditText) findViewById(R.id.etNewPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        helper = new MySQLiteOpenHelper(this);
        MyApplication myApplication = new MyApplication();
        loginUsername = myApplication.getUsername();
    }
    public void cancel(View view){
        UpdatePasswordActivity.this.finish();
    }


    public void confirm(View view){
        db = helper.getReadableDatabase();

        String inputOriPassword = etOriPassword.getText().toString();
        String newPassword = etNewPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();

        Cursor cursor = db.query("user",null,"username = ?",new String[]{loginUsername},null,null,null);
        while(cursor.moveToNext()){
             loginPassword = cursor.getString(cursor.getColumnIndex("password"));
            Log.i("loginPassword",loginPassword);
        }

        if(!inputOriPassword.equals(loginPassword)){
            Toast.makeText(UpdatePasswordActivity.this, "原密码错误！", Toast.LENGTH_SHORT).show();
        }else{

            if(!newPassword.equals(confirmPassword)){
                Toast.makeText(UpdatePasswordActivity.this, "新密码第二次输入不匹配！", Toast.LENGTH_SHORT).show();
            }else{
                ContentValues values = new ContentValues();
                values.put("password",newPassword);
                long result = db.update("user",values,"username = ?",new String[]{loginUsername});
                if(result>0){
                    Toast.makeText(UpdatePasswordActivity.this, "密码修改成功！", Toast.LENGTH_SHORT).show();
                    UpdatePasswordActivity.this.finish();
                }

            }
        }


    }



}
