package com.qiqi.ribbon;

import android.graphics.Color;

import java.util.Random;

public class RibbonConfig {
    private int[] mColors;
    private int[] mShapeTypes;
    private int mDuration = 1500;
    private Random mRandom = new Random();
    private int mCreadCount = 4;//越大图形越多
    private int Velocity = 10;//重力加速度10

    private float mMinScaleX = 0.8f;
    private float mMaxScaleX = 1.0f;
    private float mMinScaleY = 0.8f;
    private float mMaxScaleY = 1.0f;


    public int getColor() {
        if (mColors != null && mColors.length > 0) {
            return mColors[mRandom.nextInt(mColors.length)];
        }
        return Color.RED;
    }

    public RibbonShape.ShapeEntry getShape() {
        return RibbonShape.getShape(mRandom, mShapeTypes);
    }

    public float getScaleX() {
        return mMinScaleX + mRandom.nextFloat() * (mMaxScaleX - mMinScaleX);
    }

    public float mScaleY() {
        return mMinScaleY + mRandom.nextFloat() * (mMaxScaleY - mMinScaleY);
    }

    public RibbonConfig setCreadCount(int creadCount) {
        mCreadCount = creadCount;
        return this;
    }

    public RibbonConfig setColors(int[] colors) {
        mColors = colors;
        return this;
    }

    public RibbonConfig setShapeTypes(int[] shapeTypes) {
        mShapeTypes = shapeTypes;
        return this;
    }

    public RibbonConfig setDuration(int duration) {
        mDuration = duration;
        return this;
    }

    public RibbonConfig setVelocity(int velocity) {
        Velocity = velocity;
        return this;
    }

    public RibbonConfig setRandom(Random random) {
        mRandom = random;
        return this;
    }

    public RibbonConfig setMinScaleX(float minScaleX) {
        mMinScaleX = minScaleX;
        return this;
    }

    public RibbonConfig setMaxScaleX(float maxScaleX) {
        mMaxScaleX = maxScaleX;
        return this;
    }

    public RibbonConfig setMinScaleY(float minScaleY) {
        mMinScaleY = minScaleY;
        return this;
    }

    public RibbonConfig setMaxScaleY(float maxScaleY) {
        mMaxScaleY = maxScaleY;
        return this;
    }

    public int[] getColors() {
        return mColors;
    }

    public int[] getShapeTypes() {
        return mShapeTypes;
    }

    public int getDuration() {
        return mDuration;
    }

    public Random getRandom() {
        return mRandom;
    }

    public int getVelocity() {
        return Velocity;
    }

    public float getMinScaleX() {
        return mMinScaleX;
    }

    public float getMaxScaleX() {
        return mMaxScaleX;
    }

    public float getMinScaleY() {
        return mMinScaleY;
    }

    public float getMaxScaleY() {
        return mMaxScaleY;
    }

    public int getCreadCount() {
        return mCreadCount;
    }
}
