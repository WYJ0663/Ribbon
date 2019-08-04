package com.qiqi.ribbon;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.graphics.Path;

public class RibbonEntry {

    //形状路径
    public Path path;

    //中心
    public float cx = 0;
    public float cy = 0;

    public int color;

    public long startTime;//毫秒

    //缩放
    public float sx = 1;
    public float sy = 1;

    public float deSx = 0.01f;
    public float multiSx = 1;
    public boolean switchSx = false;
    //平移
    public float tx;
    public float ty;
    public float txDirection;

    //旋转
    public int degrees;
    public int degreesDirection;


    public Matrix getMatrix(Matrix matrix, Camera camera) {
        matrix.reset();
        matrix.preTranslate(tx, ty);
        matrix.preRotate(degrees, cx, cy);
        matrix.preScale(sx * multiSx, sy, cx, cy);

        return matrix;
    }
}
