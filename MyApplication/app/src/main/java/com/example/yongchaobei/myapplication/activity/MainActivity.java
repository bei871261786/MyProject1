package com.example.yongchaobei.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yongchaobei.myapplication.R;
import com.example.yongchaobei.myapplication.utils.LogUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    //调用系统相册-选择图片
    private static final int IMAGE = 1;
    @Bind(R.id.listview)
    ListView listview;
    @Bind(R.id.activity_main)
    RelativeLayout activityMain;
    private MyAdapter mAdapter;
    private ArrayList<String> mCategoryList = new ArrayList<>();
    private ArrayList<String> mNameList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i("onCreate MainActivity");
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        listview.setAdapter(mAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.TESTACTION");
                intent.addCategory(mCategoryList.get(position));
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                startActivity(intent);
            }
        });
    }

    private void init() {
        mAdapter = new MyAdapter();
        mCategoryList.add("android.intent.category.TSET1");
        mNameList.add("时钟");
        mCategoryList.add("android.intent.category.TSET2");
        mNameList.add("自定义布局初级");
        mCategoryList.add("android.intent.category.TSET3");
        mNameList.add("贝塞尔曲线");
        mCategoryList.add("android.intent.category.TSET4");
        mNameList.add("JNI测试");
    }

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mNameList.size();
        }

        @Override
        public Object getItem(int position) {
            return mNameList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_main, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.itemMainTxt.setText(mNameList.get(position));
            return convertView;
        }

        class ViewHolder {
            TextView itemMainTxt;

            public ViewHolder(View convertView) {
                itemMainTxt = (TextView) convertView.findViewById(R.id.itemMain_txt);
            }
        }
    }
}
