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

public class BinarySlidingMenu extends HorizontalScrollView {

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

    private boolean isLeftMenuOpen;
    private boolean isRightMenuOpen;

    private LinearLayout mWrapper;
    private ViewGroup mLeftMenu;
    private ViewGroup mContent;
    private ViewGroup mRightMenu;

    public BinarySlidingMenu(Context context) {
        this(context, null);
    }

    public BinarySlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BinarySlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScreenWidth = ScreenUtils.getScreenWidth(context);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SlidingMenu, defStyleAttr, 0);
        mMenuRightPadding = a.getDimensionPixelSize(R.styleable.SlidingMenu_rightPadding,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics()));
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!mOnce) {
            mWrapper = (LinearLayout) getChildAt(0);
            mLeftMenu = (ViewGroup) mWrapper.getChildAt(0);
            mContent = (ViewGroup) mWrapper.getChildAt(1);
            mRightMenu = (ViewGroup) mWrapper.getChildAt(2);

            mMenuWidth = mScreenWidth - mMenuRightPadding;
            mHalfMenuWidth = mMenuWidth / 2;
            mLeftMenu.getLayoutParams().width = mMenuWidth;
            mContent.getLayoutParams().width = mScreenWidth;
            mRightMenu.getLayoutParams().width = mMenuWidth;
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

    private boolean isOperateLeft = false;
    private boolean isOperateRight = false;

    private OnMenuOpenListener mOnMenuOpenListener;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            // Up 时，进行判断，如果显示区域大于宽度一半则完全显示，否则隐藏
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                // 如果是操作左侧菜单
                if (isOperateLeft) {
                    // 如果隐藏的区域大于菜单一半，则隐藏菜单
                    if (scrollX > mHalfMenuWidth) {
                        this.smoothScrollTo(mMenuWidth, 0);
                        // 如果当前左侧菜单开启状态，且mOnMenOpenListener不为空，则回调菜单
                        if (isLeftMenuOpen && mOnMenuOpenListener != null) {
                            mOnMenuOpenListener.onLeftMenuOpen(false);
                        }
                        isLeftMenuOpen = false;
                    } else {// 关闭左侧菜单
                        this.smoothScrollTo(0, 0);
                        // 如果当前左侧菜单是关闭状态，且mOnMenuOpenListener不为空，则回调打开菜单
                        if (!isLeftMenuOpen && mOnMenuOpenListener != null) {
                            mOnMenuOpenListener.onLeftMenuOpen(true);
                        }
                        isLeftMenuOpen = true;
                    }
                }

                // 操作右侧
                if (isOperateRight) {
                    // 如果隐藏的区域大于菜单一半，则隐藏菜单
                    if (scrollX > mHalfMenuWidth + mMenuWidth) {
                        this.smoothScrollTo(mMenuWidth + mMenuWidth, 0);
                        // 如果当前右侧菜单开启状态，且mOnMenOpenListener不为空，则回调菜单
                        if (!isRightMenuOpen && mOnMenuOpenListener != null) {
                            mOnMenuOpenListener.onRightMenuOpen(true);
                        }
                        isRightMenuOpen = true;
                    } else {
                        // 关闭右侧侧滑菜单
                        this.smoothScrollTo(mMenuWidth, 0);
                        // 如果当前右侧菜单关闭状态，且mOnMenOpenListener不为空，则回调菜单
                        if (isRightMenuOpen && mOnMenuOpenListener != null) {
                            mOnMenuOpenListener.onRightMenuOpen(false);
                        }
                        isRightMenuOpen = false;
                    }
                }


                return true;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (l > mMenuWidth) {
            isOperateRight = true;
            isOperateLeft = false;
        } else {
            isOperateRight = false;
            isOperateLeft = true;
        }
    }

    /**
     * 切换左侧菜单状态
     */
    public void toggleLeft() {
        if (isLeftMenuOpen) {
            closeLeftMenu();
        } else {
            openLeftMenu();
        }
    }

    /**
     * 打开左侧菜单
     */
    private void openLeftMenu() {
        if (isLeftMenuOpen)
            return;
        this.smoothScrollTo(0, 0);
        mOnMenuOpenListener.onLeftMenuOpen(true);
        isLeftMenuOpen = true;
    }


    /**
     * 关闭左侧菜单
     */
    private void closeLeftMenu() {
        if (isLeftMenuOpen) {
            this.smoothScrollTo(mMenuWidth, 0);
            mOnMenuOpenListener.onLeftMenuOpen(false);
            isLeftMenuOpen = false;
        }
    }

    /**
     * 切换右侧菜单状态
     */
    public void toggleRight() {
        if (isRightMenuOpen) {
            closeRightMenu();
        } else {
            openRightMenu();
        }
    }

    /**
     * 打开右侧菜单
     */
    private void openRightMenu() {
        if (isRightMenuOpen)
            return;
        this.smoothScrollTo(mMenuWidth + mMenuWidth, 0);
        mOnMenuOpenListener.onRightMenuOpen(true);
        isRightMenuOpen = true;
    }


    /**
     * 关闭右侧菜单
     */
    private void closeRightMenu() {
        if (isRightMenuOpen) {
            this.smoothScrollTo(mMenuWidth, 0);
            mOnMenuOpenListener.onRightMenuOpen(false);
            isRightMenuOpen = false;
        }
    }

    public void setOnMenuOpenListener(OnMenuOpenListener listener) {
        mOnMenuOpenListener = listener;
    }

    public interface OnMenuOpenListener {
        void onLeftMenuOpen(boolean isOpen);

        void onRightMenuOpen(boolean isOpen);
    }
}
