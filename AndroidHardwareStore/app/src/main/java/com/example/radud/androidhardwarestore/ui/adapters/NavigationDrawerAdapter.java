package com.example.radud.androidhardwarestore.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.radud.androidhardwarestore.R;
import com.example.radud.androidhardwarestore.model.NavigationDrawerItem;

import java.util.List;

/**
 * Created by radud on 06/01/2016.
 */
public class NavigationDrawerAdapter extends BaseAdapter {

    private Context mContext;
    private List<NavigationDrawerItem> mItems;
    private int mSelectedPos;

    public NavigationDrawerAdapter(Context context, List<NavigationDrawerItem> items) {
        this.mContext = context;
        this.mItems = items;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setSelectedPosition(int pos) {
        mSelectedPos = pos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.drawer_list_item, null);
        }

        TextView textView = (TextView) convertView;

        if (mSelectedPos == position) {
            textView.setBackgroundResource(R.color.list_item_pressed);
        } else {
            textView.setBackgroundResource(R.color.white);
        }

        textView.setText(mItems.get(position).getTitle());
        textView.setCompoundDrawablesWithIntrinsicBounds(mItems.get(position).getIcon(), 0, 0, 0);

        return convertView;
    }
}
