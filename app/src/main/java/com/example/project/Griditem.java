package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Griditem extends LinearLayout {
    TextView textView;
    ImageView imageView;

    public Griditem(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.griditem, this);
        textView = (TextView)findViewById(R.id.txt); // griditem.xml 에 있는 텍스트뷰
        imageView = (ImageView)findViewById(R.id.img);
    }

    public void setData(Category category){
        textView.setText(category.getName());
        imageView.setImageResource(category.getImgno());
    }
}
