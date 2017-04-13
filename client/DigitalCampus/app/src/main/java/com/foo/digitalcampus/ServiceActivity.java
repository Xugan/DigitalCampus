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
        ivUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceActivity.this,UserInfoActivity.class);
                startActivity(intent);
            }
        });
    }
}
