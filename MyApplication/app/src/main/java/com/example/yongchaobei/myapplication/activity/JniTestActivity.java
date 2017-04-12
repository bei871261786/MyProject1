package com.example.yongchaobei.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yongchaobei.myapplication.R;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.example.yongchaobei.myapplication.utils.JniUtils.helloJni;

public class JniTestActivity extends AppCompatActivity {

    @Bind(R.id.jni_bt)
    Button jniBt;
    @Bind(R.id.jni_tv)
    TextView jniTv;
    @Bind(R.id.activity_jni_test)
    RelativeLayout activityJniTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni_test);
        ButterKnife.bind(this);

        jniBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jniTv.setText(helloJni());
            }
        });
    }

}
