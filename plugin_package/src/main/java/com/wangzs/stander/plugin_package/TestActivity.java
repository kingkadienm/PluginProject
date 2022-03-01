package com.wangzs.stander.plugin_package;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.wangzs.stander.BaseActivity;

public class TestActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        TextView textView = findViewById(R.id.testTv);
        textView.setText(this.getClass().getSimpleName());

        Intent intent = getIntent();
        String wangzs = intent.getStringExtra("wangzs");
        TextView textView1 = findViewById(R.id.testTv1);
        textView1.setText(wangzs);

        findViewById(R.id.intentNew).setOnClickListener(view -> {
            Intent intent1 = new Intent(activity, TestActivity1.class);
            intent1.putExtra("wangzs","这是TestActivity传递到TestActivity1的值");
            startActivity(intent1);
        });
    }
}