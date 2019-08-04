package com.qiqi.ribbon;


import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;

import java.util.ArrayList;
import java.util.List;

public class RibbonDrawable extends Drawable {

    private Context mContext;

    private Paint mPaint;
    private Path mPath;

    private Matrix mMatrix;
    private Camera mCamera;

    private List<RibbonEntry> mRibbonList = new ArrayList<>();

    private boolean mIsStart = false;

    private long mStartTime;
    private long mPreCreateTime;

    private RibbonConfig mRibbonConfig;

    public RibbonDrawable(Context context) {
        mContext = context;
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPath = new Path();

        mMatrix = new Matrix();
        mCamera = new Camera();
    }

    public void setRibbonConfig(RibbonConfig ribbonConfig) {
        mRibbonConfig = ribbonConfig;
    }

    private void createRibbons(int width) {
        if (SystemClock.elapsedRealtime() - mStartTime > mRibbonConfig.getDuration()) {
            if (mAnimationListener != null) {
                mAnimationListener.onStopCreate();
            }
            return;
        }

        int size = mRibbonConfig.getRandom().nextInt(mRibbonConfig.getCreadCount()) + 1;
        for (int i = 0; i < size; i++) {
            RibbonShape.ShapeEntry shapeEntry = mRibbonConfig.getShape();
            if (shapeEntry != null) {
                RibbonEntry entry = new RibbonEntry();
                entry.path = shapeEntry.path;
                entry.cx = shapeEntry.cx;
                entry.cy = shapeEntry.cy;
                entry.sx = SystemUtil.dip2px(mContext, mRibbonConfig.getScaleX());
                entry.sy = SystemUtil.dip2px(mContext, mRibbonConfig.mScaleY());
                entry.startTime = SystemClock.elapsedRealtime() - 1000;
                entry.color = mRibbonConfig.getColor();
                entry.tx = mRibbonConfig.getRandom().nextInt(width);
                entry.txDirection = mRibbonConfig.getRandom().nextInt(3);
                if (entry.txDirection == 2) entry.txDirection = -1;//-1,0,1
                entry.txDirection = entry.txDirection * 1.5f;

                entry.degrees = mRibbonConfig.getRandom().nextInt(360);
                entry.degreesDirection = mRibbonConfig.getRandom().nextInt(2);
                if (entry.degreesDirection == 0) entry.degreesDirection = -1;//-1,1
                entry.degreesDirection = (entry.degreesDirection * 2);
                entry.deSx = mRibbonConfig.getRandom().nextFloat() * 0.01f + 0.01f;
                entry.multiSx = mRibbonConfig.getRandom().nextFloat();
                mRibbonList.add(entry);
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if (mRibbonConfig == null) {
            return;
        }
        long curTime = SystemClock.elapsedRealtime();

        long deCreateTime = curTime - mPreCreateTime;
        if (mIsStart && deCreateTime > 50) {
            int len = (int) (deCreateTime / 50);
            for (int i = 0; i < len || i >= 10; i++) {
                createRibbons(canvas.getWidth());
            }
            mPreCreateTime = curTime;
        }

        if (mRibbonList.isEmpty()) {
            createRibbons(canvas.getWidth());
        }

        for (int i = 0; i < mRibbonList.size(); i++) {
            RibbonEntry entry = mRibbonList.get(i);
            if (entry.ty > canvas.getHeight()) {
                mRibbonList.remove(entry);
                i--;
                continue;
            }

            long deTime = SystemClock.elapsedRealtime() - entry.startTime;
            float deltaTime = (float) deTime / 1000;
            entry.ty = entry.ty + SystemUtil.dip2px(mContext, mRibbonConfig.getVelocity() * deTime * deTime / 1000000);//s=v0t+gt^2/2
            entry.tx = entry.tx + entry.txDirection;
            entry.degrees = (int) (entry.degrees + deltaTime * entry.degreesDirection);

            float deSx = deltaTime * entry.deSx;
            float s = entry.multiSx;
            if (entry.switchSx) {
                s += deSx;
            } else {
                s -= deSx;
            }
            if (s < 0) {
                s = 0;
                entry.switchSx = !entry.switchSx;
            } else if (s > 1) {
                s = 1;
                entry.switchSx = !entry.switchSx;
            }
            entry.multiSx = s;
//            Log.i("yijunwu", "deTime=" + deTime + ",entry.degrees=" + entry.degrees + ",s=" + s);

            mPaint.setColor(entry.color);
            mPath.reset();
            mPath.addPath(entry.path);
            mPath.transform(entry.getMatrix(mMatrix, mCamera));
            canvas.drawPath(mPath, mPaint);

        }
//        Log.i("yijunwu", "mRibbonList.size()=" + mRibbonList.size());
        if (!mRibbonList.isEmpty()) {
            invalidateSelf();
        } else {
            mIsStart = false;
            if (mAnimationListener != null) {
                mAnimationListener.onStop();
            }
        }
    }

    public void start() {
        mPreCreateTime = mStartTime = SystemClock.elapsedRealtime();
        mIsStart = true;
        invalidateSelf();
        if (mAnimationListener != null) {
            mAnimationListener.onStart();
        }
    }

    private AnimationListener mAnimationListener;

    public void setAnimationListener(AnimationListener animationListener) {
        mAnimationListener = animationListener;
    }

    public interface AnimationListener {
        void onStart();

        void onStopCreate();

        void onStop();
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSPARENT;
    }

}
