package com.facefont.songhang.slidinglayout.slidingview;

import android.view.MotionEvent;

/**
 * Created by songhang on 15/11/10.
 */
public interface OnContentBorderListener {
    /**
     * 到达顶部
     * @param rawY 当前触摸位置
     */
    void onContentReachTop(float rawY);

    /**
     * 到达底部
     * @param rawY
     */
    void onContentReachBottom(float rawY);
    //点击区域松开时
    void onContentActionUp();
}