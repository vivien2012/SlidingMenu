package com.vivien.slidingmenu.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.vivien.slidingmenu.R;
import com.vivien.slidingmenu.view.MenuPageView;
import com.vivien.slidingmenu.view.SlidingMenu;

public class NormalSlidingMenuActivity extends AppCompatActivity {

    private SlidingMenu mSlidingMenu;
    private TextView mToggleMenuTv;

    private MenuPageView mMenuPageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_sliding_menu);
        initView();
    }

    private void initView() {
        mSlidingMenu = (SlidingMenu) findViewById(R.id.slidingmenu);
        mToggleMenuTv = (TextView) findViewById(R.id.toggle_menu);
        mToggleMenuTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlidingMenu.toggle();
            }
        });

        mMenuPageView = (MenuPageView) findViewById(R.id.menu_list_view);
        mMenuPageView.setOnMenuItemClickListener(new MenuPageView.OnMenuItemClickListener() {
            @Override
            public void onClick(int position) {
                mSlidingMenu.toggle();
            }
        });
    }
}
