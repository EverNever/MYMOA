package com.uestc.mymoa;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by nothisboy on 2015/7/27.
 */
public class ParentLayoutOfChildViewPager extends LinearLayout {
    private ViewPager childViewPager;

    public ParentLayoutOfChildViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ParentLayoutOfChildViewPager(Context context) {
        super(context);
    }

    private float downX;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = ev.getX();
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                if (downX == ev.getX()) {
                    if (childViewPager.getCurrentItem() == 0 ||
                            childViewPager.getCurrentItem() == childViewPager.getAdapter().getCount() - 1) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
                } else if (downX > ev.getX()) {
                    if (childViewPager.getCurrentItem() == childViewPager.getAdapter().getCount() - 1) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
                } else if (downX < ev.getX()) {
                    if (childViewPager.getCurrentItem() == 0) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
                } else {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }

    public void setChildViewPager(ViewPager viewPager) {
        this.childViewPager = viewPager;
    }
}
