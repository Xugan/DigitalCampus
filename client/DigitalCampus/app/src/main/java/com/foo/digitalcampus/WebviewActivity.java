package com.foo.digitalcampus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebviewActivity extends AppCompatActivity {
    private String webInfo;
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setUseWideViewPort(true);

        webView.getSettings().setJavaScriptEnabled(true);
        // 设置可以支持缩放 
        webView.getSettings().setSupportZoom(true);
        // 设置出现缩放工具 
        webView.getSettings().setBuiltInZoomControls(true);
        //扩大比例的缩放
        webView.getSettings().setUseWideViewPort(true);
        //自适应屏幕
       // webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgoithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);
        Intent intent = getIntent();
        webInfo = intent.getStringExtra("webInfo");
        webView.loadUrl(webInfo);

    }
}
