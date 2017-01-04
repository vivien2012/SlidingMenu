package com.vivien.slidingmenu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by vivien on 17/1/3.
 */

public class MyLinearLayout extends LinearLayout {

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setChildrenDrawingOrderEnabled(true);
    }

    @Override
    protected int getChildDrawingOrder(int childCount, int i) {

        if (i == 0) {
            return 1;
        }

        if (i == 2) {
            return 2;
        }

        if (i == 1) {
            return 0;
        }

        return super.getChildDrawingOrder(childCount, i);
    }
}
