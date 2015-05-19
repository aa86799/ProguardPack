package com.stone.myapplication;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * author : stone
 * time   : 15/4/20 14 55
 * email  : aa86799@163.com
 */
public class MyAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(null, R.layout.my_text_view, null);
            holder = new ViewHolder(convertView);
        }
        return convertView;
    }

    public class ViewHolder {
        public final TextView tvtxt;
        public final View root;

        public ViewHolder(View root) {
            tvtxt = (TextView) root.findViewById(R.id.tv_txt);
            this.root = root;
        }
    }
}
