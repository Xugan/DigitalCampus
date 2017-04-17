package com.ontime.blueteethdemo;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private IntentFilter intentFilter;
    private MyBroadcastReciever reciever;
    private boolean success = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //intentFilter = new IntentFilter("com.ontime.bluetooth");
        //intentFilter = new IntentFilter();
        //intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//        reciever = new MyBroadcastReciever();
//        registerReceiver(reciever,intentFilter);
//
//        Intent intent = new Intent("com.ontime.bluetooth");
//        sendBroadcast(intent);
        ConnectivityManager manager = (ConnectivityManager) this.getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo.State state = manager.getNetworkInfo(manager.TYPE_WIFI).getState();
        if(state.CONNECTED == state){
            success = true;
        }
        state = manager.getNetworkInfo(manager.TYPE_MOBILE).getState();
        if(state.CONNECTED == state){
            success = true;
        }
        if(!success){
            Toast.makeText(this,"网络连接失败",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(reciever);
    }
}
