package com.wangzs.pluginproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView =findViewById(R.id.text);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

            if (Environment.isExternalStorageManager()) {

            } else {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.setData(Uri.parse("package:" + this.getPackageName()));
                startActivityForResult(intent, 1);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        } else {

        }
    }


    /**
     * 跳转到插件
     *
     * @param view
     */
    public void intentPlugin(View view) {

        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "plugin_package-debug.apk");

        PackageManager packageManager = getPackageManager();
        //插件包的路径
        PackageInfo packageArchiveInfo = packageManager.getPackageArchiveInfo(file.getAbsolutePath(), PackageManager.GET_ACTIVITIES);
        //获取到插件包里的activity
        ActivityInfo[] activities = packageArchiveInfo.activities;
        //取第一个Activity
        ActivityInfo activityInfo = activities[0];


        Intent intent = new Intent(this, ProxyActivity.class);
        intent.putExtra("className", activityInfo.name);
        startActivity(intent);
    }

    /**
     * 加载插件包
     *
     * @param view
     */
    public void loadPlugin(View view) {
        PluginManager.getInstance(this).loadPlugin(Environment.getExternalStorageDirectory() + File.separator + "plugin_package-debug.apk");
    }
}