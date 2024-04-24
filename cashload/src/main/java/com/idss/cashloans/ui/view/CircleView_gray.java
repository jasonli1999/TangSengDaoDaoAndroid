package com.idss.cashloans.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.idss.cashloans.R;

public class CircleView_gray extends View {
    private Paint mPaint;

    public CircleView_gray(Context context) {
        super(context);
        init();
    }

    public CircleView_gray(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.gray_8b8b8b));
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = Math.min(centerX, centerY);
        canvas.drawCircle(centerX, centerY, radius, mPaint);
    }
}