package com.ontime.videoviewdemo;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    private VideoView videoView;
    private RelativeLayout containerVideo;
    private RelativeLayout containerIv;
    private ImageView ivPic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
        containerIv = (RelativeLayout) findViewById(R.id.containerIv);
        containerVideo = (RelativeLayout) findViewById(R.id.containerVideo);
        ivPic = (ImageView) findViewById(R.id.ivPic);
        containerVideo.setVisibility(View.INVISIBLE);
        Uri uri = Uri.parse( "android.resource://com.ontime.videoviewdemo/"+R.raw.nike );
        ivPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                containerVideo.setVisibility(View.VISIBLE);
                containerIv.setVisibility(View.INVISIBLE);
            }
        });
        videoView = (VideoView)this.findViewById(R.id.videoView );


        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                containerVideo.setVisibility(View.INVISIBLE);
                containerIv.setVisibility(View.VISIBLE);
            }
        });
        //设置视频控制器
        videoView.setMediaController(new MediaController(this));

        //播放完成回调
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.start();
            }
        });

        //设置视频路径
        videoView.setVideoURI(uri);

        //去除边框，LayoutParams构造方法实现全屏
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.FILL_PARENT,
                RelativeLayout.LayoutParams.FILL_PARENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        videoView.setLayoutParams(layoutParams);

        //开始播放视频
        videoView.start();



//        setContentView(R.layout.activity_main);
//        videoview = (VideoView) findViewById(R.id.videoView);
//        //videoview.setVideoURI(Uri.parse("/res/raw/nike.mp4"));
//        MediaController controller = new MediaController(this);
//        videoview.setMediaController(controller);
//
//        String videoUrl = Environment.getExternalStorageDirectory().getPath()+"/nike.mp4";
//        Uri uri = Uri.parse(videoUrl);
//        videoview.setVideoURI(uri);
//        videoview.start();
    }
}
