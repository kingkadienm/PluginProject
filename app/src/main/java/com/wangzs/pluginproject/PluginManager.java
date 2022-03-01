package com.wangzs.pluginproject;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * @Description:
 * @Author: wangzs
 * @Date: 2022-03-01 12:26
 * @Version:
 */
public class PluginManager {


    private static final String TAG = PluginManager.class.getName();

    private static PluginManager pluginManager;
    private DexClassLoader dexClassLoader;
    private Resources resources;

    public PluginManager(Context context) {
        this.context = context;
    }

    private Context context;

    public static PluginManager getInstance(Context context) {
        if (pluginManager == null) {
            synchronized (PluginManager.class) {
                if (pluginManager == null) {
                    pluginManager = new PluginManager(context);
                }
            }
        }
        return pluginManager;
    }

    /**
     * 加载插件包的apk文件
     *
     * @param fileApkPath
     */
    public void loadPlugin(String fileApkPath) {
        if (TextUtils.isEmpty(fileApkPath)) {
            return;
        }
        try {


            File apkFile = new File(fileApkPath);
            if (!apkFile.exists()) {
                Log.e(TAG, "loadPlugin: 插件不存在");
                return;
            }
            //插件缓存目录 dexClassLoader需要一个缓存目录
            String optimizedDirectory = context.getDir("pluginCachePath", Context.MODE_PRIVATE).getAbsolutePath();

            //Activity class
            dexClassLoader = new DexClassLoader(apkFile.getAbsolutePath(), optimizedDirectory, null, context.getClassLoader());


            AssetManager assetManager = AssetManager.class.newInstance();
            Method assetManagerMethod = assetManager.getClass().getMethod("addAssetPath", String.class);
            assetManagerMethod.invoke(assetManager, apkFile.getAbsolutePath());

            Resources appResources = context.getResources();

            resources = new Resources(assetManager, appResources.getDisplayMetrics(), appResources.getConfiguration());

        } catch (Exception e) {
            Log.e(TAG, "loadPlugin: " + e.getMessage());
        }
    }


    public DexClassLoader getDexClassLoader() {
        return dexClassLoader;
    }

    public Resources getResources() {
        return resources;
    }

    public void releasePlugin(){
        context = null;
        resources = null;
        dexClassLoader = null;
        pluginManager = null;
    }
}
