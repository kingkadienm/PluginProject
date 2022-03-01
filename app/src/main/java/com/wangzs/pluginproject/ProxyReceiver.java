package com.wangzs.pluginproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.wangzs.stander.BaseReceiver;

public class ProxyReceiver extends BroadcastReceiver {

    private String receiverName;

    public ProxyReceiver(String name) {
        this.receiverName = name;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Class receiverClass = PluginManager.getInstance(context).getDexClassLoader().loadClass(receiverName);
            Object receive = receiverClass.newInstance();
            BaseReceiver baseReceiver = (BaseReceiver) receive;
            baseReceiver.onReceive(context, intent);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
