package com.wangzs.stander;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

/**
 * @Description:
 * @Author: wangzs
 * @Date: 2022-03-01 15:37
 * @Version:
 */
public interface ServiceInterface {


    void insertAppContext(Service activity);

    IBinder onBind(Intent intent);


    void onCreate();


    void onStart(Intent intent, int startId);


    int onStartCommand(Intent intent, int flags, int startId);


    void onDestroy();
}
