package com.foo.digitalcampus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class UserInfoActivity extends AppCompatActivity {
    private TextView tvBack;
    private EditText etUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        tvBack = (TextView) findViewById(R.id.tvBack);
        etUsername = (EditText) findViewById(R.id.etUsername);
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfoActivity.this.finish();
            }
        });
    }
}
