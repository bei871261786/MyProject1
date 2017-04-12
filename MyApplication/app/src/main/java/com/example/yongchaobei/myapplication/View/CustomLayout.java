package com.example.yongchaobei.myapplication.View;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.yongchaobei.myapplication.utils.LogUtils;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2017/4/10 11:51
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class CustomLayout extends ViewGroup {

    public CustomLayout(Context context) {
        super(context);
    }

    public CustomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childWidth = getWidth() / getChildCount();
        int chileHeight = getHeight() / getChildCount();
        LogUtils.i("onLayout childSize:" + getWidth() + ":::" + childWidth + "::::" + getHeight() + ":::" + chileHeight);
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.layout(childWidth * i, chileHeight * i, childWidth * (i+1), chileHeight * (i+1));
            LogUtils.i("onLayout forchilrsize:" + i + "::::" + child.getLeft() + ":::" + child.getTop() + "::::" + child.getRight() + ":::" + child.getBottom()+">>>"+child.getMeasuredWidth()+">>>"+child.getHeight());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

}
