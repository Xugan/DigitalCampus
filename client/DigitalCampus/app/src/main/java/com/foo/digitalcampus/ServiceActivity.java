package com.foo.digitalcampus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ServiceActivity extends AppCompatActivity {
    private ImageView ivUserInfo;
    private ImageView ivOfficialNet;
    private ImageView ivEducationalSystem;
    private ImageView ivSchoolCalendar;
    private ImageView ivUpdatePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ivUserInfo = (ImageView) findViewById(R.id.ivUserInfo);
        ivOfficialNet = (ImageView) findViewById(R.id.ivOfficialNet);
        ivEducationalSystem = (ImageView) findViewById(R.id.ivEducationalSystem);
        ivSchoolCalendar = (ImageView) findViewById(R.id.ivSchoolCalendar);
        ivUpdatePassword = (ImageView) findViewById(R.id.ivUpdatePassword);

        //监听个人信息图标的点击事件
        ivUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击跳转个人信息页面
                Intent intent = new Intent(ServiceActivity.this,UserInfoActivity.class);
                startActivity(intent);
            }
        });

        ivOfficialNet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceActivity.this,WebviewActivity.class);
                intent.putExtra("webInfo","http://www.henu.edu.cn/");
                startActivity(intent);
            }
        });

        ivEducationalSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceActivity.this,WebviewActivity.class);
                intent.putExtra("webInfo","http://xk.henu.edu.cn/cas/login.action");
                startActivity(intent);
            }
        });

        ivSchoolCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceActivity.this,WebviewActivity.class);
                intent.putExtra("webInfo","http://www.henu.edu.cn/html/xyfw/xlcx/1.html");
                startActivity(intent);
            }
        });

        ivUpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceActivity.this,UpdatePasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}
