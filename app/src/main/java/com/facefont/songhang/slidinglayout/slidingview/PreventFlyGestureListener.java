package com.facefont.songhang.slidinglayout.slidingview;

import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by songhang on 15/11/11.
 * 禁止fly事件
 */
public class PreventFlyGestureListener extends GestureDetector.SimpleOnGestureListener {
    boolean isPreventFly; //是否阻止fly

    /**
     * 快速滚动
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return isPreventFly;
    }

    public void setIsPreventFly(boolean isPreventFly) {
        this.isPreventFly = isPreventFly;
    }
}
