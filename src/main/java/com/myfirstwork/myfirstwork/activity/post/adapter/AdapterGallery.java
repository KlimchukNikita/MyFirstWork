package com.myfirstwork.myfirstwork.activity.post.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.TextView;

import com.myfirstwork.myfirstwork.R;

public class AdapterGallery extends BaseAdapter {

    private Context context;
    private float scale;
    private int backgroundItem;
    public AdapterGallery(Context context){
        this.context=context;
        scale = context.getResources().getDisplayMetrics().scaledDensity;
        TypedArray attr  = context.obtainStyledAttributes(R.styleable.MyGallery);
        backgroundItem=attr.getResourceId(R.styleable.MyGallery_ItemBackground,0);
        attr.recycle();
    }
    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Gallery.LayoutParams layoutParams = new Gallery.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        TextView textView = new TextView(context);
        switch (position){
            case 0:
                textView.setText(R.string.work);
                break;
            case 1:
                textView.setText(R.string.finder);
                break;
            case 2:
                textView.setText(R.string.bloger);
                break;
        }
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(12*scale);
        textView.setPadding(150,0,150,0);
        textView.setTextColor(Color.WHITE);
        textView.setBackgroundResource(backgroundItem);
        textView.setLayoutParams(layoutParams);

        return textView;
    }
}
