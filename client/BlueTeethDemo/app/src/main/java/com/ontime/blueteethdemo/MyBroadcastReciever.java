package com.ontime.blueteethdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;

/**
 * Created by shgl1hz1 on 2017/4/17.
 */

public class MyBroadcastReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"broadcastReciever",Toast.LENGTH_LONG).show();
    }
}
