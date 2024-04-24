package com.idss.cashloans.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.idss.cashloans.R;

public class CircleView extends View {

    private Paint fillPaint;  // 实心圆的画笔
    private Paint strokePaint;  // 空心圆的画笔

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        fillPaint = new Paint();
        fillPaint.setColor(Color.WHITE);
        fillPaint.setStyle(Paint.Style.FILL);

        strokePaint = new Paint();
        strokePaint.setColor(getResources().getColor(R.color.yellow_c68b03));
        strokePaint.setStyle(Paint.Style.STROKE);    //修改这里改实心圆还是空心圆
        strokePaint.setStrokeWidth(8);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = Math.min(centerX, centerY) - 10;

        canvas.drawCircle(centerX, centerY, radius, fillPaint);
        canvas.drawCircle(centerX, centerY, radius, strokePaint);
    }
}