package com.facefont.songhang.slidinglayout.slidingview;

import android.view.MotionEvent;

/**
 * Created by songhang on 15/11/10.
 */
public interface OnContentBorderListener {
    /**
     * 到达顶部
     */
    void onContentReachTop();

    /**
     * 到达底部
     */
    void onContentReachBottom();
}