package com.qiqi.ribbon;

import android.content.Context;
import android.graphics.Path;

import java.util.Random;

public class RibbonShape {

    public static final int CIRCLE = 0;
    public static final int RECT_LONG = 1;
    public static final int RECT = 2;
    public static final int TRIANGLE = 3;
    public static final int STAR = 4;

    public static ShapeEntry getShape(Random random, int[] types) {
        if (types != null && types.length > 0) {
            int type = random.nextInt(types.length);
            switch (type) {
                case CIRCLE:
                    return getCircle();
                case RECT_LONG:
                    return getRectLong();
                case RECT:
                    return getRect();
                case TRIANGLE:
                    return getTriangle();
                case STAR:
                    return getStar();
            }
        }
        return getRect();
    }

    public static ShapeEntry getTriangle() {
        Path path = new Path();
        path.moveTo(0, 0);
        path.lineTo(10, 0);
        path.lineTo(5, 8.6f);
        ShapeEntry entry = new ShapeEntry();
        entry.path = path;
        entry.cx = 5;
        entry.cy = 4.3f;
        return entry;
    }

    public static ShapeEntry getRect() {
        Path path = new Path();
        path.addRect(0, 0, 10, 10, Path.Direction.CW);
        ShapeEntry entry = new ShapeEntry();
        entry.path = path;
        entry.cx = 5;
        entry.cy = 5;
        return entry;
    }

    public static ShapeEntry getRectLong() {
        Path path = new Path();
        path.addRect(0, 0, 7, 21, Path.Direction.CW);
        ShapeEntry entry = new ShapeEntry();
        entry.path = path;
        entry.cx = 3.5f;
        entry.cy = 10.5f;
        return entry;
    }

    public static ShapeEntry getCircle() {
        Path path = new Path();
        path.addCircle(5, 5, 5, Path.Direction.CW);
        ShapeEntry entry = new ShapeEntry();
        entry.path = path;
        entry.cx = 5;
        entry.cy = 5;
        return entry;
    }

    public static ShapeEntry getStar() {
        int rFive = 20;
        float xA = 20;
        float yA = 0;
        float xB = 0;
        float xC = 0;
        float xD = 0;
        float xE = 0;
        float yB = 0;
        float yC = 0;
        float yD = 0;
        float yE = 0;
        xD = (float) (xA - rFive * Math.sin(Math.toRadians(18)));
        xC = (float) (xA + rFive * Math.sin(Math.toRadians(18)));
        yD = yC = (float) (yA + Math.cos(Math.toRadians(18)) * rFive);
        yB = yE = (float) (yA + Math.sqrt(Math.pow((xC - xD), 2) - Math.pow((rFive / 2), 2)));
        xB = xA + (rFive / 2);
        xE = xA - (rFive / 2);
        Path path = new Path();
        path.moveTo(xA, yA);//a
        path.lineTo(xC, yC);//c
        path.lineTo(xE, yE);//e
        path.lineTo(xB, yB);//b
        path.lineTo(xD, yD);//d
        path.lineTo(xA, yA);//a
//        Log.i("yijunwu", xA + " " + yA + " - " + xC + " " + yC + " - " + xE + " " + yE + " - " + xB + " " + yB + " - " + xD + " " + yD);
        ShapeEntry entry = new ShapeEntry();
        entry.path = path;
        entry.cx = 15;
        entry.cy = 10;
        return entry;
    }

    public static int dip2px(Context context, float dipValue) {
        return SystemUtil.dip2px(context, dipValue);
    }

    public static class ShapeEntry {
        //形状路径
        public Path path;

        //中心
        public float cx = 0;
        public float cy = 0;
    }

}
