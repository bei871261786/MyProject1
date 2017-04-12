package com.example.yongchaobei.myapplication.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.yongchaobei.myapplication.R;
import com.example.yongchaobei.myapplication.utils.DateUtils;
import com.example.yongchaobei.myapplication.utils.LogUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2017/4/5 15:54
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class CustomView extends View {
    private float mBorderWidth;
    private int mBorderColor;
    private int mHourColor;
    private int mSecondColor;
    private int mMinusColor;
    private Paint mPaint;
    private float width;
    private float height;
    private float radius;
    private RectF mBounds;
    private float mLargeLengh = 40;
    private float mSmallLengh = 10;
    private Path mPath;
    private float mSweep;
    private TimerTask mTask;
    private Timer mTimer;
    private int mHourPosition = 0;
    private int mMinutePosition = 0;
    private int mSecondPosition = 0;
    private int mTempPosition;


    public CustomView(Context context) {
        super(context);
        init();
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView, 0, 0);
        try {
            mBorderColor = typedArray.getColor(R.styleable.CustomView_border_color, getContext().getResources().getColor(R.color.colorPrimary));
            mBorderWidth = typedArray.getDimension(R.styleable.CustomView_border_width, 2);
        } finally {
            typedArray.recycle();
        }
        init();
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView, 0, 0);
        try {
            mBorderColor = typedArray.getColor(R.styleable.CustomView_border_color, getContext().getResources().getColor(R.color.colorPrimary));
            mHourColor = typedArray.getColor(R.styleable.CustomView_hour_color, getContext().getResources().getColor(R.color.colorPrimary));
            mSecondColor = typedArray.getColor(R.styleable.CustomView_second_color, getContext().getResources().getColor(R.color.colorPrimary));
            mMinusColor = typedArray.getColor(R.styleable.CustomView_minus_color, getContext().getResources().getColor(R.color.colorPrimary));
            mBorderWidth = typedArray.getDimension(R.styleable.CustomView_border_width, 2);
        } finally {
            typedArray.recycle();
        }
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mBorderWidth);
        mPaint.setColor(mBorderColor);
        mPath = new Path();
        mTimer = new Timer();
        mTask = new TimerTask() {
            @Override
            public void run() {
                resetNeedle();
            }
        };
        mTimer.schedule(mTask, 1000, 1000);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        LogUtils.i("CustomView onDraw" + mBounds + "::::" + mPaint);
        super.onDraw(canvas);
        mPaint.setColor(0x66555555);
        canvas.drawRoundRect(new RectF(mBounds.centerX() - (float) 0.9 * width / 2, mBounds.centerY() - (float) 0.9 * height / 2, mBounds.centerX() + (float) 0.9 * width / 2, mBounds.centerY() + (float) 0.9 * height / 2), 30, 30, mPaint);
        mPaint.setColor(mBorderColor);
        canvas.drawCircle(mBounds.centerX(), mBounds.centerY(), radius, mPaint);
        double angle;
        int n;
        float startx;
        float starty;
        float endx;
        float endy;

        for (int i = 0; i < 60; i++) {
            LogUtils.i("CustomView  foti" + i);
            n = 6 * i;
            angle = Math.toRadians(n);
            startx = (float) (Math.sin(angle) * radius + mBounds.centerX());
            starty = (float) (Math.cos(angle) * radius + mBounds.centerY());
            if (i % 5 == 0) {
                endx = (float) (Math.sin(angle) * (radius + mLargeLengh) + mBounds.centerX());
                endy = (float) (Math.cos(angle) * (radius + mLargeLengh) + mBounds.centerY());
            } else {
                endx = (float) (Math.sin(angle) * (radius + mSmallLengh) + mBounds.centerX());
                endy = (float) (Math.cos(angle) * (radius + mSmallLengh) + mBounds.centerY());
            }
            LogUtils.i("CustomView  foti:" + angle + ":::" + Math.sin(angle) + ":::" + Math.cos(angle));
            canvas.drawLine(startx, starty, endx, endy, mPaint);
        }
        canvas.drawCircle(mBounds.centerX(), mBounds.centerY(), 20, mPaint);
        Path path = new Path();
        path.addCircle(mBounds.centerX(), mBounds.centerY(), radius + 50, Path.Direction.CW);
        canvas.drawPath(mPath, mPaint);
    }

    public void resetNeedle() {
        mSecondPosition++;
        if (mSecondPosition % 60 == 0) {
            mMinutePosition++;
        }
        if ((mTempPosition != mMinutePosition) && mMinutePosition % 60 == 0) {
            mHourPosition++;
        }
        mTempPosition = mMinutePosition;
        LogUtils.i("reset position:" + mSecondPosition + "::::" + mMinutePosition + ":::::" + mHourPosition);
        mPath = new Path();
        int n = 6 * mHourPosition;
        float xh = (float) (Math.sin(Math.toRadians(6 * mHourPosition)) * radius * 0.3 + mBounds.centerX());
        float yh = (float) (mBounds.centerY()-Math.cos(Math.toRadians(6 * mHourPosition)) * radius * 0.3 );
        float xm = (float) (Math.sin(Math.toRadians(6 * mMinutePosition)) * radius * 0.5 + mBounds.centerX());
        float ym = (float) (mBounds.centerY()-Math.cos(Math.toRadians(6 * mMinutePosition)) * radius * 0.5 );
        float xs = (float) (Math.sin(Math.toRadians(6 * mSecondPosition)) * radius * 0.8 + mBounds.centerX());
        float ys = (float) (mBounds.centerY()-Math.cos(Math.toRadians(6 * mSecondPosition)) * radius * 0.8 );
        mPath.moveTo(mBounds.centerX(), mBounds.centerY());
        mPath.lineTo(xh, yh);
        mPath.moveTo(mBounds.centerX(), mBounds.centerY());
        mPath.lineTo(xm, ym);
        mPath.moveTo(mBounds.centerX(), mBounds.centerY());
        mPath.lineTo(xs, ys);
        postInvalidate();
    }


    public void adjustTime() {
        String time = DateUtils.formatDateHmm(System.currentTimeMillis());
        LogUtils.i("adjustTime time:" + time);
        int hour = Integer.parseInt(time.substring(0, 2));
        hour=hour-12;
        int minute = Integer.parseInt(time.substring(3, 5));
        int second = Integer.parseInt(time.substring(6, 8));
        LogUtils.i("adjustTime hms:" + hour + "::::" + minute + "::::" + second);
        mHourPosition=hour*60/12+minute/12;
        mMinutePosition=minute;
        mSecondPosition=second;
        LogUtils.i("adjustTime position:" + mHourPosition + "::::" + mMinutePosition + "::::" + mSecondPosition);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        LogUtils.i("CustomView onSizeChanged");
        super.onSizeChanged(w, h, oldw, oldh);
        mBounds = new RectF(getLeft(), getTop(), getRight(), getBottom());
        width = mBounds.right - mBounds.left;
        height = mBounds.bottom - mBounds.top;
        if (width <= height) {
            radius = width / 4;
        } else {
            radius = height / 4;
        }
    }
}
