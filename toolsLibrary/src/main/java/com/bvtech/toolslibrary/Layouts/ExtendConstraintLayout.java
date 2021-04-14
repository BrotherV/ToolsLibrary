package com.bvtech.toolslibrary.layouts;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * Created by Mohsen on 9/10/2018.
 */

public class ExtendConstraintLayout extends ConstraintLayout {

    private int xPoint, yPoint;
    private boolean isTouched;
    private boolean isMoving;

    public ExtendConstraintLayout(Context context) {
        super(context);
    }

    public ExtendConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExtendConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public int getXPoint() {
        return xPoint;
    }

    public int getYPoint() {
        return yPoint;
    }

    public boolean isTouched() {
        return isTouched;
    }

    public boolean isMoving() {
        return isMoving;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        /*
         * This method JUST determines whether we want to intercept the motion.
         * If we return true, onTouchEvent will be called and we do the actual
         * scrolling there.
         */
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xPoint = (int) ev.getX();
                yPoint = (int) ev.getY();
                isTouched = true;
                break;
            case MotionEvent.ACTION_MOVE:
                isMoving = true;
                break;
            case MotionEvent.ACTION_UP:
                isTouched = false;
                isMoving = false;
                break;
        }

        // In general, we don't want to intercept touch events. They should be
        // handled by the child view.
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }
}
