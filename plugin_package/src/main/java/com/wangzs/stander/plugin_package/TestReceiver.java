package com.wangzs.stander.plugin_package;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.wangzs.stander.BaseReceiver;

/**
 * @Description:
 * @Author: wangzs
 * @Date: 2022-03-01 16:24
 * @Version:
 */
public class TestReceiver extends BroadcastReceiver implements BaseReceiver {

    private static final String TAG = TestReceiver.class.getSimpleName();


    @Override
    public void onReceive(Context context, Intent intent) {
        String wangzs = intent.getStringExtra("wangzs");
        Log.e(TAG, "onReceive: " + wangzs);
    }
}
