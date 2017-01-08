package com.jacky.keshe.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jacky.keshe.R;
import com.jacky.keshe.view.DotLinearLayout;
import com.jacky.keshe.view.MyViewFliper;

/**
 * Created by Jacky on 2017/1/5.
 */

public class HomeFragment extends Fragment {

    private MyViewFliper viewFliper;
    private DotLinearLayout dots;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        initView(view);
        initEvent();

        return view;
    }

    private void initView(View v) {
        viewFliper = (MyViewFliper)v.findViewById(R.id.vf_home_guanggao);
        dots = (DotLinearLayout) v.findViewById(R.id.dots);
    }
    private void initEvent() {
        int []view = {
                R.drawable.shouye_guanggao,
                R.drawable.xiezou_guanggao,

        };
        dots.addDot(view.length);
        dots.dotShow(0);
        viewFliper.addImageView(view);
        viewFliper.autoFliper(3000);
        MyViewFliper.OnScrollListener listener = new MyViewFliper.OnScrollListener(){
            @Override
            public void getCount(int i) {
                dots.dotShow(i);
            }
        };
        viewFliper.setOnScrollListener(listener);
    }
}
