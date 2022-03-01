package com.wangzs.pluginproject;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.wangzs.stander.ActivityInterface;

import java.lang.reflect.Constructor;

/**
 * @Description: 代理Activity  使用代理的activity来加载插件里的activity
 * @Author: wangzs
 * @Date: 2022-03-01 12:53
 * @Version:
 */
public class ProxyActivity extends Activity {

    /**
     * 获取到pluginManager里的resource 不用activity的
     *
     * @return
     */
    @Override
    public Resources getResources() {
        return PluginManager.getInstance(this).getResources();
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance(this).getDexClassLoader();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String className = intent.getStringExtra("className");
        try {
            Class mPluginActivityClass = getClassLoader().loadClass(className);
            //实例化 插件里的activity
            Constructor constructor = mPluginActivityClass.getConstructor(new Class[]{});
            Object mPluginActivity = constructor.newInstance(new Object[]{});
            ActivityInterface activityInterface = (ActivityInterface) mPluginActivity;
            //将插件里的activity注入到app里
            activityInterface.insertAppContent(this);
            activityInterface.onCreate(new Bundle());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void startActivity(Intent intent) {
        String className = intent.getStringExtra("className");
        Intent proxyIntent = new Intent(this, ProxyActivity.class);
        proxyIntent.putExtra("className", className);
        proxyIntent.putExtras(intent.getExtras());
        super.startActivity(proxyIntent);
    }
}
