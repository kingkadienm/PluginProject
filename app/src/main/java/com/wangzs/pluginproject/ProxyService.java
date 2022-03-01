package com.wangzs.pluginproject;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.wangzs.stander.ServiceInterface;

public class ProxyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        try {
            String className = intent.getStringExtra("className");
            Class serviceClass = PluginManager.getInstance(this).getDexClassLoader().loadClass(className);
            Object service = serviceClass.newInstance();
            ServiceInterface serviceInterface = (ServiceInterface) service;
            serviceInterface.insertAppContext(this);
            serviceInterface.onStartCommand(intent,flags,startId);
        } catch ( Exception e) {
            e.printStackTrace();
        }


        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
