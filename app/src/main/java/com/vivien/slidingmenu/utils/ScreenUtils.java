package com.vivien.slidingmenu.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

/**
 * 屏幕工具类
 */
public class ScreenUtils {


    /**
     * dp转px
     */
    public static int dp2px(Context context, int px) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px,
                context.getResources().getDisplayMetrics());
    }

    /**
     * dp转px
     */
    public static int sp2px(Context context, int px) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, px,
                context.getResources().getDisplayMetrics());
    }


    /**
     * dp转px
     */
    public static int dip2px(float dpValue, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * px转dp
     */
    public static int px2dip(float pxValue, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * 取屏幕宽度
     *
     * @return
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 取屏幕高度
     *
     * @return
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels - getStatusBarHeight(context);
    }

    /**
     * 取屏幕高度包含状态栏高度
     *
     * @return
     */
    public static int getScreenHeightWithStatusBar(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    /**
     * 取导航栏高度
     *
     * @return
     */
    public static int getNavigationBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    /**
     * 取状态栏高度
     *
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 25;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int getActionBarHeight(Context context) {
        int actionBarHeight = 0;

        final TypedValue tv = new TypedValue();
        if (context.getTheme()
                .resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(
                    tv.data, context.getResources().getDisplayMetrics());
        }
        return actionBarHeight;
    }

    /**
     * 获取控件的高度
     */
    public static int getViewMeasuredHeight(View view) {
        calculateViewMeasure(view);
        return view.getMeasuredHeight();
    }

    /**
     * 获取控件的宽度
     */
    public static int getViewMeasuredWidth(View view) {
        calculateViewMeasure(view);
        return view.getMeasuredWidth();
    }

    /**
     * 测量控件的尺寸
     */
    private static void calculateViewMeasure(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

        view.measure(w, h);
    }


}
