package com.foo.digitalcampus;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class UserInfoActivity extends AppCompatActivity {
    private TextView tvBack;
    private EditText etUsername;
    private TextView tvEdit;
    private EditText etStuNum;
    private EditText etDepartment;
    private EditText etPhone;
    private EditText etMail;
    private ImageView ivPhoto;
    public static final int CHOOSE_PHOTO = 2;
    private SQLiteDatabase db;
    private MySQLiteOpenHelper helper;
    private String imagePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ivPhoto = (ImageView) findViewById(R.id.ivPhoto);
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

        ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(UserInfoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(UserInfoActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    openAlbum();
                }
            }
        });


        helper = new MySQLiteOpenHelper(this);
        //db = helper.getReadableDatabase();


}


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= 19) {  //4.4及以上的系统使用这个方法处理图片；
                        handleImageOnKitKat(data);
                    } else {
                        handleImageBeforeKitKat(data);  //4.4及以下的系统使用这个方法处理图片
                    }
                }
            default:
                break;
        }
    }

    //打开相册
    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }


    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }


    /**
     * 4.4及以上的系统使用这个方法处理图片
     *
     * @param data
     */
    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
         imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            //如果document类型的Uri,则通过document来处理
            String docID = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docID.split(":")[1];     //解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;

                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/piblic_downloads"), Long.valueOf(docID));

                imagePath = getImagePath(contentUri, null);

            }

        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            //如果是content类型的uri，则使用普通方式使用
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            //如果是file类型的uri，直接获取路径即可
            imagePath = uri.getPath();

        }

        displayImage(imagePath);
    }


    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            ivPhoto.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }


    private String getImagePath(Uri uri, String selection) {
         imagePath = null;
        //通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                imagePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return imagePath;
    }


    public void commit(View view){
        db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("photo_url",imagePath);
        values.put("name",etUsername.getText().toString());
        values.put("stu_num",etStuNum.getText().toString());
        values.put("depart",etDepartment.getText().toString());
        values.put("phone",etPhone.getText().toString());
        values.put("mail",etMail.getText().toString());
        long result = db.insert("user_info",null,values);
        if(result>0){
            Toast.makeText(UserInfoActivity.this, "提交成功！", Toast.LENGTH_SHORT).show();
        }
    }
}
