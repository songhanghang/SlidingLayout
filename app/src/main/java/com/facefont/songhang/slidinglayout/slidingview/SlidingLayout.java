package com.facefont.songhang.slidinglayout.slidingview;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import com.facefont.songhang.slidinglayout.R;

/**
 * Created by songhang on 15/11/10.
 * 滑动布局
 */
public class SlidingLayout extends LinearLayout implements OnContentBorderListener {
    private static final long ANIM_DURATION = 300; //动画时间
    private int maxHeight; //最大高度
    private int handleId; 
    private boolean isTop; //是否到达顶部
    private Rect rectFrame = new Rect();
    private boolean isHandleTarcking; //是否handle开始滑动
    private float downY; //按下时的位置
    private boolean enAbleSlide = true; //是否可以滑动
    private OnSlidingListener onSlidingListener;

    private View handleBar; // 顶部拖动view
    private View contentView; // 内容的View

    public void setOnSlidingListener(OnSlidingListener onSlidingListener) {
        this.onSlidingListener = onSlidingListener;
    }

    public void setEnAbleSlide(boolean enAbleSlide) {
        this.enAbleSlide = enAbleSlide;
    }

    public SlidingLayout(Context context) {
        this(context, null, 0);
    }

    public SlidingLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getStyleable(context, attrs, defStyleAttr);
    }

    public void setContentView(View view) {
        this.contentView = view;
        if (this.contentView == null)
            return;
        int count = getChildCount();
        //移除已经有content view
        if (count == 2) {
            removeViewAt(1);
        }
        addView(contentView);
    }

    public void onContentActionUp() {
        if (enAbleSlide) {
            if (maxHeight - getY() <= maxHeight / 5 * 4) {
                closeAnim(false);
            } else {
                openAnim(false);
            }
        }
    }

    private void getStyleable(final Context context, final AttributeSet attributeSet, final int defStyleAttr) {
        final TypedArray typedArray = context.obtainStyledAttributes(attributeSet,
                R.styleable.SlidingLayout,
                defStyleAttr, 0);
        handleId = typedArray.getResourceId(R.styleable.SlidingLayout_handle_id, 0);
        if (handleId == 0) {
            throw new IllegalArgumentException("The handle attribute is required and must refer to a valid child.");
        }
        typedArray.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (maxHeight == 0) {
            maxHeight = ((View)getParent()).getHeight();
            //捕获到最大高度以后设置隐藏
            if (maxHeight > 0) {
                setVisibility(GONE);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        handleBar = findViewById(handleId);
        if (handleBar == null) {
            throw new IllegalArgumentException("The handle attribute is must refer to an existing child.");
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("song", "onInterceptTouchEvent");
        if (enAbleSlide) {
            handleBar.getHitRect(rectFrame);
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                downY = ev.getRawY();
                Log.i("onInterceptTouchEvent", "downY -----------: " + downY);
            }

            float x = ev.getX();
            float y = ev.getY();
            //触摸区域在handler bar时 || 到达顶部 拦截触摸事件
            isHandleTarcking = rectFrame.contains((int) x, (int) y) || isTop;
            return isHandleTarcking;
        } else {
            return super.onInterceptTouchEvent(ev);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (enAbleSlide) {
            moveHandle(event);
            if (event.getAction() == MotionEvent.ACTION_DOWN && isHandleTarcking) {
                return true;
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                isTop = false;
            }
        }
        return super.onTouchEvent(event);
    }

    private void moveHandle(MotionEvent event) {
        int action = event.getAction();
        float y = event.getRawY();
        float moveY = y - downY;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                float height = maxHeight - moveY;
                //防止高度超过最大高度
                if (height <= maxHeight) {
                    setY(moveY);
                } else {
                    setY(0);
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                onContentActionUp();
                break;
        }
    }

    /**
     * 主动唤起
     */
    public void openFormOrigin() {
        openAnim(true);
    }

    /**
     * 主动关闭
     */
    public void closeFromOrigin() {
        closeAnim(true);
    }

    /**
     * @param isfromOrigin 是否从起点开始打开
     */
    private void openAnim(boolean isfromOrigin) {
        setVisibility(VISIBLE);
        if (onSlidingListener != null) {
            onSlidingListener.open();
        }
        anim(isfromOrigin ? maxHeight : getY(), 0, new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                setY(0);
                if (onSlidingListener != null) {
                    onSlidingListener.opened();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    /**
     * @param isfromOrigin 是否从起点开始关闭
     */
    private void closeAnim(boolean isfromOrigin) {
        if (onSlidingListener != null) {
            onSlidingListener.close();
        }
        anim(isfromOrigin ? 0 : getY(), maxHeight, new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                setY(maxHeight);
                setVisibility(GONE);
                if (onSlidingListener != null) {
                    onSlidingListener.closed();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void anim(float start, float end, Animator.AnimatorListener animatorListener) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "y", start, end).setDuration(ANIM_DURATION);
        objectAnimator.addListener(animatorListener);
        objectAnimator.start();
    }

    @Override
    public void onContentReachTop() {
        if (enAbleSlide) {
            isTop = true;
            requestDisallowInterceptTouchEvent(false);
        }
    }

    @Override
    public void onContentReachBottom() {

    }
}
