package com.example.dell.exerciseapp.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 学习自定义View相关类
 * https://blog.csdn.net/abcdef314159/article/details/51720686
 */

public class CustomView extends View {

    Paint paintText ;

    public CustomView(Context context) {
        this(context,null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        paintText = new Paint();

        // Paint 对应不同需求（样式）有主要以下几种
        // 至于效果，自己自行演示
        paintText.setStyle(Paint.Style.FILL);
        paintText.setStyle(Paint.Style.FILL);
        paintText.setStyle(Paint.Style.FILL);

        paintText.setStrokeCap(Paint.Cap.BUTT);
        paintText.setStrokeCap(Paint.Cap.ROUND);
        paintText.setStrokeCap(Paint.Cap.SQUARE);

        paintText.setStrokeJoin(Paint.Join.BEVEL);
        paintText.setStrokeJoin(Paint.Join.MITER);
        paintText.setStrokeJoin(Paint.Join.ROUND);

        paintText.setTextAlign(Paint.Align.CENTER);
        paintText.setTextAlign(Paint.Align.LEFT);
        paintText.setTextAlign(Paint.Align.RIGHT);

        // Paint  重要的方法
        paintText.reset();
        // 设置抗锯齿
        paintText.setAntiAlias(true);
        //设置抖动，线条会更柔和
        paintText.setDither(true);
        // 设置一些标识（下划线，抗锯齿）
        paintText.setFlags(0);
        // 设置线性文本是否需要缓存（true 不要缓存，反之）
        paintText.setLinearText(true);
        //设置亚像素，是对文本的一种优化设置，可以让文字看起来更加清晰明显，可以参考一下PC端的控制面板-外观和个性化-调整ClearType文本
        paintText.setSubpixelText(true);





    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
