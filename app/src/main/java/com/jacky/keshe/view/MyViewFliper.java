package com.jacky.keshe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.jacky.keshe.R;

/**
 * Created by Jacky on 2017/1/5.
 */

public class MyViewFliper extends ViewFlipper {

     static public class OnScrollListener{
        public void filpperLeft(){}
        public void filpperRight(){}
         public void getCount(int i){}
    }

    private Context context;
    private int imgSize;
    private OnScrollListener listener;

    public MyViewFliper(Context context) {
        super(context);
    }

    public MyViewFliper(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public void addImageView(int []view){
        imgSize = view.length;
        for (int v : view){
            addView(v);
        }
    }

    @Override
    public void showNext() {
        super.showNext();
        if (listener!=null){
            listener.filpperRight();
            listener.getCount(getDisplayedChild());
        }
//        if(getDisplayedChild()!=imgSize-1){
//            super.showNext();
//        }
    }

    @Override
    public void showPrevious() {
        super.showPrevious();
        if (listener!=null){
            listener.filpperLeft();
            listener.getCount(getDisplayedChild());
        }
//        if(getDisplayedChild()!=0){
//            super.showPrevious();
//        }
    }

    public void autoFliper(int ms){
        setFlipInterval(ms);
        setInAnimation(context, R.anim.right_in);
        setOutAnimation(context, R.anim.right_out);
        startFlipping();
    }

    public void addView(int id) {
        ImageView image = getImageView(id);
        super.addView(image);
    }

    public void setOnScrollListener(OnScrollListener listener){
        this.listener = listener;
    }

    private ImageView getImageView(int id){
        ImageView imageView = new ImageView(context);
        imageView.setBackgroundResource(id);
        return imageView;
    }

    private float startX;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                stopFlipping();
                startX=event.getX();
                Log.d("MotionEvent","down");
                return true;
            case MotionEvent.ACTION_UP:
                //向右滑动
                Log.d("MotionEvent","UP");
                startFlipping();
                if(event.getX()-startX>50){
                    setInAnimation(context, R.anim.left_in);
                    setOutAnimation(context,R.anim.left_out);
                    showPrevious();
                }
                //向左滑动
                if(startX-event.getX()>50) {
                    setInAnimation(context,R.anim.right_in);
                    setOutAnimation(context,R.anim.right_out);
                    showNext();
                }
                return true;
        }
        return super.onTouchEvent(event);
    }
}