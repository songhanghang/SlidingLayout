package com.facefont.songhang.slidinglayout.slidingview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * Created by songhang on 15/11/19.
 */
public class SlidingGridView extends GridView {
    private OnContentBorderListener onBorderListener;

    public SlidingGridView(Context context) {
        super(context);
    }

    public SlidingGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlidingGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnBorderListener(OnContentBorderListener onBorderListener) {
        this.onBorderListener = onBorderListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        if (onBorderListener != null) {
            if (isTouchEvent) {
                if (deltaY < 0) {
                    onBorderListener.onContentReachTop();
                } else if (deltaY > 0) {
                    onBorderListener.onContentReachBottom();
                }
            }
        }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }
}
