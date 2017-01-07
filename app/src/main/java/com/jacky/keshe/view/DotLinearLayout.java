package com.jacky.keshe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jacky.keshe.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacky on 2017/1/5.
 */

public class DotLinearLayout extends LinearLayout{

    private Context context;
    private List<ImageView> views;

    public DotLinearLayout(Context context) {
        super(context);
        this.context = context;
    }

    public DotLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public DotLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public void addDot(int count){
        views = new ArrayList<>(count);
        for (int i =0;i<count;i++){
            ImageView im = new ImageView(context);
            LayoutParams params = new LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            params.setMargins(10,10,10,10);
            im.setLayoutParams(params);
            im.setBackgroundResource(R.drawable.icon_point);
            addView(im);
            views.add(im);
        }
    }

    public void dotShow(int count){
        for(ImageView view:views){
            view.setBackgroundResource(R.drawable.icon_point);
        }
        views.get(count).setBackgroundResource(R.drawable.icon_point_pre);
    }
}
