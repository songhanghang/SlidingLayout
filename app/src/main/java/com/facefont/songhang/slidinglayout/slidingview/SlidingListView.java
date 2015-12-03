package com.facefont.songhang.slidinglayout.slidingview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;
/**
 * Created by songhang on 15/11/10.
 */
public class SlidingListView extends ListView {
    private OnContentBorderListener onBorderListener;
    private float curDownY; //当前按下位置
    public SlidingListView(Context context) {
        this(context, null);
    }

    public SlidingListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnBorderListener(OnContentBorderListener onBorderListener) {
        this.onBorderListener = onBorderListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_MOVE:
                curDownY = ev.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                if (onBorderListener != null) {
                    onBorderListener.onContentActionUp();
                }
                break;
        }
        return super.onTouchEvent(ev);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }


    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        if (onBorderListener != null && isTouchEvent) {
                if (deltaY < 0) {
                    onBorderListener.onContentReachTop(curDownY);
                } else if (deltaY > 0) {
                    onBorderListener.onContentReachBottom(curDownY);
                }
            }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }
}
