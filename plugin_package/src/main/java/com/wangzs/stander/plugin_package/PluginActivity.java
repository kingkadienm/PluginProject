package com.wangzs.stander.plugin_package;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.wangzs.stander.BaseActivity;

public class PluginActivity extends BaseActivity {
    private TestReceiver testReceiver = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin);

        findViewById(R.id.intentNew).setOnClickListener(view -> {
            Intent intent = new Intent(activity, TestActivity.class);
            intent.putExtra("wangzs", "这是PluginActivity传递到TestActivity的值");
            startActivity(intent);
        });
        findViewById(R.id.startService).setOnClickListener(view -> {

            Intent intent = new Intent(activity, TestService.class);

            startService(intent);
        });

        findViewById(R.id.regiestReceive).setOnClickListener(view -> {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("wangzs");
            testReceiver = new TestReceiver();
            registerReceiver(new TestReceiver(), intentFilter);
        });
        findViewById(R.id.unRegiestReceiver).setOnClickListener(view -> {
            unregisterReceiver(testReceiver);
        });
        findViewById(R.id.sendReceiver).setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setAction("wangzs");
            intent.putExtra("wangzs", "ssssssssssss");
            sendBroadcast(intent);
        });
    }
}