package com.foo.digitalcampus;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.BDNotifyListener;//假如用到位置提醒功能，需要import该类
import com.baidu.location.Poi;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportActivity extends AppCompatActivity {
    private Spinner spSchools;
    private Spinner spCases;
    //校区下拉框选项
    private String[] schools = {"明伦校区","金明校区","龙子湖校区"};
    //案卷类型下拉框选项
    private String[] cases = {"校园环境","公共设施","教学设备","办公设备","寝室","园林绿化","紧急突发事件","其他类型"};
    private ArrayAdapter<String> schoolAdapter;
    private ArrayAdapter<String> caseAdapter;
    private Button mTakePhoto;
    private Button mChoosePhoto;
    private ImageView picture;
    private TextView tvShowLocation;
    public static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PHOTO = 2;
    private Uri imageUri;
    private MySQLiteOpenHelper helper;
    private SQLiteDatabase db;
    private String spSchoolSelect ;
    private String spCaseSelect;
    private String imagePath;
    private String date;
    private String caseContent;
    private EditText etContent;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        tvShowLocation = (TextView) findViewById(R.id.tvShowLocation);
        spCases = (Spinner) findViewById(R.id.spCases);
        spSchools = (Spinner) findViewById(R.id.SpSchools);
        etContent = (EditText) findViewById(R.id.et_content);

        //Log.i("caseContent",caseContent);
        //校区下拉框绑定适配器
        schoolAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,schools);
        spSchools.setAdapter(schoolAdapter);

        spSchools.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spSchoolSelect = spSchools.getSelectedItem().toString();
                //Toast.makeText(ReportActivity.this,spSchoolSelect,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //案件下拉框绑定适配器
        caseAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,cases);
        spCases.setAdapter(caseAdapter);
        spCases.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spCaseSelect = spCases.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mTakePhoto = (Button) findViewById(R.id.btCamera);
        mChoosePhoto = (Button) findViewById(R.id.btChoosePic);
        picture = (ImageView) findViewById(R.id.ivPhoto);

        //监听摄像头的点击事件
        mTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建file对象，用于存储拍照后的图片；
                File outputImage = new File(getExternalCacheDir(), "output_image.jpg");

                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (Build.VERSION.SDK_INT >= 24) {
                    imageUri = FileProvider.getUriForFile(ReportActivity.this,
                            "com.gyq.cameraalbumtest.fileprovider", outputImage);
                } else {
                    imageUri = Uri.fromFile(outputImage);
                }

                //启动相机程序
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);

            }
        });

        mChoosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(ReportActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ReportActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    openAlbum();
                }
            }
        });





        tvShowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportActivity.this,BaiduMapActivity.class);
                startActivity(intent);
            }
        });


        helper = new MySQLiteOpenHelper(this);

        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        Date currentDate = new Date(System.currentTimeMillis());
        date = format.format(currentDate);
        Log.i("ReportActivity Date",date);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bm = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));

                        //Log.i("takePhoto",imagePath);
                        picture.setImageBitmap(bm);
                        imagePath = getImagePath(imageUri,null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                break;
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
         imagePath = getImagePath(uri, null);
        displayImage(imagePath);
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

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            picture.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
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

    public void commit(View view){
        db = helper.getReadableDatabase();
        caseContent = etContent.getText().toString();
        ContentValues values = new ContentValues();
        values.put("campus_area",spSchoolSelect);
        values.put("case_type",spCaseSelect);
        values.put("image_url",imagePath);
        values.put("content",caseContent);
        values.put("date",date);
        //Log.i("Report imagePath",imagePath);
        Log.i("Report content",caseContent);
        long result = db.insert("report",null,values);
        if(result>0){
            Toast.makeText(ReportActivity.this, "commit success!", Toast.LENGTH_SHORT).show();
        }
        ReportActivity.this.finish();

    }



}
