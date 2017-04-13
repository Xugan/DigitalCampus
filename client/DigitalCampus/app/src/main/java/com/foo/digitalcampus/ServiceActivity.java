package com.foo.digitalcampus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ServiceActivity extends AppCompatActivity {
    private ImageView ivUserInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ivUserInfo = (ImageView) findViewById(R.id.ivUserInfo);
        //监听个人信息图标的点击事件
        ivUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击跳转个人信息页面
                Intent intent = new Intent(ServiceActivity.this,UserInfoActivity.class);
                startActivity(intent);
            }
        });
    }
}
