package com.ontime.dialogdemo;

import android.app.AliasActivity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    //private Dialog dialog;
    private AlertDialog alertDialog;
    private ImageView imageView ;
    private View layoutView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         layoutView = LayoutInflater.from(this).inflate(R.layout.dialog_view,null);
        //imageView = (ImageView) view.findViewById(R.id.ivPic);

    }
    public void click(View view){
//        dialog = new Dialog(MainActivity.this);
        //        dialog.setTitle("input password!");
        //        dialog.show();
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("haha")
                .setMessage("确定要删除？")
                .setView(layoutView)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })

                .create()
                .show();
    }
}
