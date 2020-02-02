package com.example.project;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    Context context;
    ArrayList<Category> categories = new ArrayList<>();

    public GridAdapter(Context context, ArrayList<Category> categories){
        this.context = context;
        this.categories = categories;
    }
    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = new Griditem(context);
        }
        ((Griditem)convertView).setData(categories.get(position));
        return convertView;
    }
}
