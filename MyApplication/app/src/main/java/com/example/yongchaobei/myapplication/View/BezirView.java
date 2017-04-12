package com.example.yongchaobei.myapplication.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import static android.content.ContentValues.TAG;

/**
 * ************************************************
 * 作    者：yongchao.bei
 * 版    本：1.0
 * 创建日期：2017/4/11 10:44
 * 描    述：
 * 修订历史：
 * ************************************************
 */

public class BezirView extends View {
    Paint mPaint;
    Point mStartPoint;
    Point mEndPoint;
    Point mAssistPoint;
    Path mPath;

    public BezirView(Context context) {
        super(context);
        init();
    }

    public BezirView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BezirView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPath = new Path();
        mStartPoint = new Point(300, 600);
        mEndPoint = new Point(900, 600);
        mAssistPoint = new Point(600, 900);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);

        mPath.reset();
        mPath.moveTo(mStartPoint.x, mStartPoint.y);
        mPath.quadTo(mAssistPoint.x, mAssistPoint.y, mEndPoint.x, mEndPoint.y);
        canvas.drawPath(mPath, mPaint);
        canvas.drawPoint(mAssistPoint.x, mAssistPoint.y, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                mAssistPoint.x = (int) event.getX();
                mAssistPoint.y = (int) event.getY();
                Log.i(TAG, "assistPoint.x = " + mAssistPoint.x);
                Log.i(TAG, "assistPoint.Y = " + mAssistPoint.y);
                invalidate();
                  break;
        }
        return true;
    }
}
