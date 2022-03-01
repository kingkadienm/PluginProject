package com.wangzs.stander.plugin_package;

import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.wangzs.stander.BaseService;

/**
 * @Description:
 * @Author: wangzs
 * @Date: 2022-03-01 15:43
 * @Version:
 */
public class TestService extends BaseService {

    public static final String TAG = TestService.class.getSimpleName();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        Log.e(TAG, "run: ..............");
                    }
                }

            }
        }).start();

        return super.onStartCommand(intent, flags, startId);
    }
}
