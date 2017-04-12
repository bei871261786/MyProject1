package com.example.yongchaobei.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.yongchaobei.myapplication.R;
import com.example.yongchaobei.myapplication.View.CustomView;
import com.example.yongchaobei.myapplication.utils.LogUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2017/4/5 17:57
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class CustomViewActivity extends AppCompatActivity {

    @Bind(R.id.bt)
    Button bt;
    @Bind(R.id.cust1)
    CustomView cust1;
    @Bind(R.id.activity_main)
    LinearLayout activityMain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i("onCreate CustomViewActivity");
        setContentView(R.layout.activity_customview);
        ButterKnife.bind(this);
        cust1.adjustTime();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cust1.adjustTime();
            }
        });
    }

}
