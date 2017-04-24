package com.foo.digitalcampus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class UpdatePasswordActivity extends AppCompatActivity {
    private EditText etOriPassword;
    private EditText etNewPassword;
    private EditText etConfirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        etOriPassword = (EditText) findViewById(R.id.etOriPassword);
        etNewPassword = (EditText) findViewById(R.id.etNewPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);

    }
}
