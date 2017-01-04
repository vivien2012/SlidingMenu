package com.vivien.slidingmenu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vivien.slidingmenu.R;

import java.util.ArrayList;

/**
 * Created by vivien on 17/1/3.
 */

public class MenuListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> mMenuDatas = new ArrayList<>();

    public MenuListAdapter(Context context, ArrayList<String> datas) {
        this.mContext = context;
        this.mMenuDatas = datas;
    }

    @Override
    public int getCount() {
        return mMenuDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mMenuDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_menu, parent, false);
            holder = new ViewHolder();
            holder.tips = (TextView) convertView.findViewById(R.id.item_menu_tips);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tips.setText(mMenuDatas.get(position));

        return convertView;
    }

    private class ViewHolder {
        private TextView tips;
    }
}
