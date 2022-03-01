package com.wangzs.stander;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;

/**
 * @Description:
 * @Author: wangzs
 * @Date: 2022-03-01 12:23
 * @Version:
 */
public class BaseActivity implements ActivityInterface {

    private static final String TAG = BaseActivity.class.getName();
    /**
     * 宿主的环境
     */
    public Activity activity;


    @Override
    public void insertAppContent(Activity appActivity) {
        this.activity = appActivity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "onCreate: " + activity.getClass().getName());
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {

    }

    public void setContentView(int resId) {
        activity.setContentView(resId);
    }


    public <T extends View> T findViewById(@IdRes int id) {
        return activity.findViewById(id);
    }

    public void startActivity(Intent intent) {
        Intent intentNew = new Intent();
        intentNew.putExtra("className", intent.getComponent().getClassName());
        Bundle extras = intent.getExtras();
        intentNew.putExtras(extras);
        activity.startActivity(intentNew);
    }

    public Intent getIntent() {
        return activity.getIntent();
    }

    public ComponentName startService(Intent service) {
        Intent intentNew = new Intent();
        intentNew.putExtra("className", service.getComponent().getClassName());
        return activity.startService(intentNew);
    }
    // 注册广播, 使用宿主环境-appActivity

    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        return activity.registerReceiver(receiver, filter);
    }

    // 发送广播, 使用宿主环境-appActivity
    public void sendBroadcast(Intent intent) {
        activity.sendBroadcast(intent);
    }

    public void unregisterReceiver(BroadcastReceiver receiver) {
        activity.unregisterReceiver(receiver);
    }
}
