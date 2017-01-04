package com.vivien.slidingmenu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.vivien.slidingmenu.R;
import com.vivien.slidingmenu.adapter.MenuListAdapter;

import java.util.ArrayList;

/**
 * Created by vivien on 16/12/29.
 */

public class MenuPageView extends LinearLayout implements AdapterView.OnItemClickListener {

    private Context mContext;

    private ListView mListView;

    private ArrayList<String> mMenuDatas = new ArrayList<>();

    private OnMenuItemClickListener mOnMenuItemClickListener;

    public MenuPageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        View view = View.inflate(context, R.layout.view_menu, this);
        initData();
        mListView = (ListView) view.findViewById(R.id.menu_list);
        MenuListAdapter adapter = new MenuListAdapter(mContext, mMenuDatas);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
    }

    private void initData() {
        mMenuDatas.add("Item Menu One");
        mMenuDatas.add("Item Menu Two");
        mMenuDatas.add("Item Menu Three");
        mMenuDatas.add("Item Menu Four");
        mMenuDatas.add("Item Menu Five");
        mMenuDatas.add("Item Menu Six");
        mMenuDatas.add("Item Menu Seven");
        mMenuDatas.add("Item Menu Eight");
        mMenuDatas.add("Item Menu Nine");
        mMenuDatas.add("Item Menu Ten");
        mMenuDatas.add("Item Menu Eleven");
        mMenuDatas.add("Item Menu Twelve");

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mOnMenuItemClickListener.onClick(position);
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener listener) {
        mOnMenuItemClickListener = listener;
    }

    public interface OnMenuItemClickListener {
        void onClick(int position);
    }

}
