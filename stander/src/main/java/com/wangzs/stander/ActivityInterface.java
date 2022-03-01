package com.wangzs.stander;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;

/**
 * @Description:
 * @Author: wangzs
 * @Date: 2022-03-01 12:12
 * @Version:
 */
public interface ActivityInterface {

    /**
     * 把宿主的环境给到插件，插件是没有app运行环境的
     *
     * @param appActivity
     */
    void insertAppContent(Activity appActivity);

    //对应Activity的生命周期方法

    void onCreate(Bundle savedInstanceState);



    void onResume();

    void onStart();

    void onPause();

    void onRestart();

    void onStop();

    void onDestroy();

    void onConfigurationChanged(@NonNull Configuration newConfig);
}
