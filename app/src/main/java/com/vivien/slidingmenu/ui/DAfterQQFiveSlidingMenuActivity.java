package com.vivien.slidingmenu.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.vivien.slidingmenu.R;
import com.vivien.slidingmenu.adapter.ContentListAdapter;
import com.vivien.slidingmenu.view.BinarySlidingMenuV3;
import com.vivien.slidingmenu.view.MenuPageView;

import java.util.ArrayList;

public class DAfterQQFiveSlidingMenuActivity extends AppCompatActivity {

    private BinarySlidingMenuV3 mBSlidingMenu;

    private TextView mOpenLeftMenuTv;
    private TextView mOpenRightMenuTv;

    private MenuPageView mMenuPageLeftView;
    private MenuPageView mMenuPageRightView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dafter_qqfive_sliding_menu);

        mBSlidingMenu = (BinarySlidingMenuV3) findViewById(R.id.slidingmenu);
        mBSlidingMenu.setOnMenuOpenListener(new BinarySlidingMenuV3.OnMenuOpenListener() {
            @Override
            public void onLeftMenuOpen(boolean isOpen) {
                Log.e("left menu is", (isOpen ? "open" : "close"));
            }

            @Override
            public void onRightMenuOpen(boolean isOpen) {
                Log.e("right menu is", (isOpen ? "open" : "close"));
            }
        });

        mOpenLeftMenuTv = (TextView) findViewById(R.id.left_toggle_menu);
        mOpenRightMenuTv = (TextView) findViewById(R.id.right_toggle_menu);
        mOpenLeftMenuTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBSlidingMenu.toggleLeft();
            }
        });
        mOpenRightMenuTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBSlidingMenu.toggleRight();
            }
        });

        ListView listView = (ListView) findViewById(R.id.main_list);
        ArrayList<String> mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'Z'; i++) {
            mDatas.add("Item Content " + (char) i);
        }
        listView.setAdapter(new ContentListAdapter(this, mDatas));

        mMenuPageLeftView = (MenuPageView) findViewById(R.id.menu_list_view);
        mMenuPageLeftView.setOnMenuItemClickListener(new MenuPageView.OnMenuItemClickListener() {
            @Override
            public void onClick(int position) {
                mBSlidingMenu.toggleLeft();
            }
        });

        mMenuPageRightView = (MenuPageView) findViewById(R.id.menu_list_view2);
        mMenuPageRightView.setOnMenuItemClickListener(new MenuPageView.OnMenuItemClickListener() {
            @Override
            public void onClick(int position) {
                mBSlidingMenu.toggleRight();
            }
        });
    }
}
