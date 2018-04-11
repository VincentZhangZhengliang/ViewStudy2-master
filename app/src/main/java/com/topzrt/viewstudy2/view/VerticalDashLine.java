package com.topzrt.viewstudy2.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.topzrt.viewstudy2.R;

/**
 * Created by Vincent;
 * Created on 2018/1/25;
 * DSC:
 */

public class VerticalDashLine extends View {


    private Paint mPaint;
    private int   mWidth;
    private int   mHeight;
    private int   widthSize;
    private int   heightSize;

    /**
     * 虚线的方向
     */
    public static final int ORIENTATION_HORIZONTAL = 0;
    public static final int ORIENTATION_VERTICAL   = 1;

    private static final String TAG                 = "DashView";
    public static final  int    DEFAULT_DASH_WIDTH  = 100;
    public static final  int    DEFAULT_LINE_WIDTH  = 100;
    public static final  int    DEFAULT_LINE_HEIGHT = 10;
    public static final  int    DEFAULT_LINE_COLOR  = 0xFF0000;

    /**
     * 默认为水平方向
     */
    public static final int DEFAULT_DASH_ORIENTATION = ORIENTATION_VERTICAL;
    private int   dashOrientation;
    /**
     * 间距宽度
     */
    private float dashWidth;
    /**
     * 线段高度
     */
    private float lineHeight;
    /**
     * 线段宽度
     */
    private float lineWidth;
    /**
     * 线段颜色
     */
    private int   lineColor;
    float   totalHeight = 0;
    float[] points      = new float[]{0, totalHeight, 0, dashWidth};

    public VerticalDashLine(Context context) {
        this(context, null);
    }

    public VerticalDashLine(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalDashLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.VerticalDashLine);
        dashWidth = typedArray.getDimension(R.styleable.VerticalDashLine_dashWidth, DEFAULT_DASH_WIDTH);
        lineHeight = typedArray.getDimension(R.styleable.VerticalDashLine_lineHeight, DEFAULT_LINE_HEIGHT);
        lineWidth = typedArray.getDimension(R.styleable.VerticalDashLine_lineWidth, DEFAULT_LINE_WIDTH);
        lineColor = typedArray.getColor(R.styleable.VerticalDashLine_lineColor, DEFAULT_LINE_COLOR);
        dashOrientation = typedArray.getInteger(R.styleable.VerticalDashLine_dashOrientation, DEFAULT_DASH_ORIENTATION);
        typedArray.recycle();
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setColor(lineColor);
        mPaint.setStrokeWidth(lineWidth);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        widthSize = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
        heightSize = MeasureSpec.getSize(heightMeasureSpec - getPaddingTop() - getPaddingBottom());
        if (dashOrientation == ORIENTATION_HORIZONTAL) {
            setMeasuredDimension(widthSize, (int) lineHeight);
        } else {
            setMeasuredDimension((int) lineWidth, heightSize);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (dashOrientation == ORIENTATION_VERTICAL) {
            while (totalHeight < heightSize) {
                canvas.drawPoints(points, mPaint);
                totalHeight = totalHeight + lineHeight + dashWidth;
                canvas.translate(0,totalHeight);
            }
        }
    }
}
