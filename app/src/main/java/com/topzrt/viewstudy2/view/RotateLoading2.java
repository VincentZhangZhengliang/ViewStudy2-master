package com.topzrt.viewstudy2.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by Vincent;
 * Created on 2017/12/20;
 * DSC:
 */

public class RotateLoading2 extends View {


    private int   mCenterX;
    private int   mCenterY;
    private Paint mPaint;
    private int   mlength;
    private RectF mRectF;

    private final static int     DEFAULT_SWEEP_ANGLE = 80;
    private              int     topStartAngle       = 10;
    private              int     bottomStartAbgle    = 190;
    private              int     sweepAngle          = DEFAULT_SWEEP_ANGLE;
    private              int     speedOfDegree       = 5;
    private              float   speedOfArc          = speedOfDegree / 4;
    private              boolean changeBigger        = true;

    public RotateLoading2(Context context) {
        this(context, null);
    }

    public RotateLoading2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RotateLoading2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(8);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w / 2;
        mCenterY = h / 2;
        mlength = w / 4;
        mRectF = new RectF(-mlength, -mlength, mlength, mlength);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mCenterX, mCenterY);
        //绘制两个圆弧
        canvas.drawArc(mRectF, topStartAngle, sweepAngle, false, mPaint);
        canvas.drawArc(mRectF, bottomStartAbgle, sweepAngle, false, mPaint);

        topStartAngle += speedOfDegree;
        bottomStartAbgle += speedOfDegree;

        if (topStartAngle > 360) {
            topStartAngle = topStartAngle - 360;
        }

        if (topStartAngle > 360) {
            bottomStartAbgle = bottomStartAbgle - 360;
        }

        if (changeBigger) {
            if (sweepAngle < 160) {
                sweepAngle += speedOfArc;
                invalidate();
            }
        } else {
            if (sweepAngle > speedOfDegree) {
                sweepAngle -= 2 * speedOfArc;
                invalidate();
            }
        }
        if (sweepAngle >= 160 || sweepAngle <= 10) {
            changeBigger = !changeBigger;
            invalidate();
        }
    }

    public void startAnimator() {
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(this, "scaleX", 0.0f, 1);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(this, "scaleY", 0.0f, 1);
        scaleXAnimator.setDuration(300);
        scaleXAnimator.setInterpolator(new LinearInterpolator());
        scaleYAnimator.setDuration(300);
        scaleYAnimator.setInterpolator(new LinearInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleXAnimator, scaleYAnimator);
        animatorSet.start();
    }

    public void stopAnimator() {
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(this, "scaleX", 1, 0);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(this, "scaleY", 1, 0);
        scaleXAnimator.setDuration(300);
        scaleXAnimator.setInterpolator(new LinearInterpolator());
        scaleYAnimator.setDuration(300);
        scaleYAnimator.setInterpolator(new LinearInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleXAnimator, scaleYAnimator);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();
    }
}
