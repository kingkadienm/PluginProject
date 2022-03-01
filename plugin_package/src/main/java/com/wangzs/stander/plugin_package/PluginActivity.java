package com.wangzs.stander.plugin_package;

import android.content.Intent;
import android.os.Bundle;

import com.wangzs.stander.BaseActivity;

public class PluginActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin);

        findViewById(R.id.intentNew).setOnClickListener(view -> {
            Intent intent = new Intent(activity, TestActivity.class);
            intent.putExtra("wangzs","这是PluginActivity传递到TestActivity的值");
            startActivity(intent);
        });
    }
}