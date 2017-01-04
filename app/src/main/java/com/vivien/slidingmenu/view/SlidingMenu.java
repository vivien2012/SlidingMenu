package com.vivien.slidingmenu.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.vivien.slidingmenu.R;
import com.vivien.slidingmenu.utils.ScreenUtils;

/**
 * Created by vivien on 16/12/29.
 */

public class SlidingMenu extends HorizontalScrollView {

    /**
     * 屏幕的宽度
     */
    private int mScreenWidth;

    /**
     * 50dp
     */
    private int mMenuRightPadding = 50;

    private int mMenuWidth;
    private int mHalfMenuWidth;

    private boolean mOnce;

    private boolean isOpen;

    public SlidingMenu(Context context) {
        this(context, null);
    }

    public SlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScreenWidth = ScreenUtils.getScreenWidth(context);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SlidingMenu, defStyleAttr, 0);
        mMenuRightPadding = a.getDimensionPixelSize(R.styleable.SlidingMenu_rightPadding,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics()));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!mOnce) {
            LinearLayout wrapper = (LinearLayout) getChildAt(0);
            ViewGroup menu = (ViewGroup) wrapper.getChildAt(0);
            ViewGroup content = (ViewGroup) wrapper.getChildAt(1);
            // dp to px
//            mMenuRightPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
//                    mMenuRightPadding, content.getResources().getDisplayMetrics());
            mMenuWidth = mScreenWidth - mMenuRightPadding;
            mHalfMenuWidth = mMenuWidth / 2;
            menu.getLayoutParams().width = mMenuWidth;
            content.getLayoutParams().width = mScreenWidth;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            // 将菜单隐藏
            this.scrollTo(mMenuWidth, 0);
            mOnce = true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            // Up 时，进行判断，如果显示区域大于宽度一半则完全显示，否则隐藏
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                if (scrollX > mHalfMenuWidth) {
                    this.smoothScrollTo(mMenuWidth, 0);
                    isOpen = false;
                } else {
                    this.smoothScrollTo(0, 0);
                    isOpen = true;
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 切换菜单状态
     */
    public void toggle() {
        if (isOpen) {
            closeMenu();
        } else {
            openMenu();
        }
    }

    /**
     * 打开菜单
     */
    private void openMenu() {
        if (isOpen)
            return;
        this.smoothScrollTo(0, 0);
        isOpen = true;
    }

    /**
     * 关闭菜单
     */
    private void closeMenu() {
        if (isOpen) {
            this.smoothScrollTo(mMenuWidth, 0);
            isOpen = false;
        }
    }
}
