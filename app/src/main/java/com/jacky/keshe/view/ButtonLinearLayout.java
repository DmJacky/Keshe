package com.jacky.keshe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Jacky on 2017/1/6.
 */

public class ButtonLinearLayout extends LinearLayout {
    private Context context;
    public ButtonLinearLayout(Context context) {
        super(context);
        this.context = context;
    }

    public ButtonLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public ButtonLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public void addButton(int btnImgId, String btnName){
        LayoutParams params = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(VERTICAL);

        ImageView img = new ImageView(context);

        img.setImageResource(btnImgId);

        TextView btnTxt = new TextView(context);
        btnTxt.setText(btnName);

        linearLayout.addView(img);
        linearLayout.addView(btnTxt);

        addView(linearLayout);
    }

    public void clickButton(){

    }
}
