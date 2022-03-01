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
public abstract class BaseService implements ServiceInterface {

    /**
     * 宿主层的service
     */
    private Service service;

    @Override
    public void insertAppContext(Service service) {
        this.service = service;
    }

    @Override
    public abstract IBinder onBind(Intent intent);

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart(Intent intent, int startId) {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return 0;
    }

    @Override
    public void onDestroy() {

    }
}
