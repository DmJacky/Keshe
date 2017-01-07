package com.jacky.keshe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jacky.keshe.Fragment.HomeFragment;
import com.jacky.keshe.Fragment.MineFragment;
import com.jacky.keshe.Fragment.OrderFragment;
import com.jacky.keshe.Utils.SharedPreferencesUtils;


/**
 * Created by Jacky on 2017/1/5.
 */

public class HomepageActivity extends AppCompatActivity {
    private LinearLayout btnHome;
    private LinearLayout btnOrder;
    private LinearLayout btnMine;
    private ImageView imgHome;
    private ImageView imgOrder;
    private ImageView imgMine;
    private TextView txtHome;
    private TextView txtOrder;
    private TextView txtMine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        userLogin();

        initView();
    }

    private void userLogin(){
        if(!SharedPreferencesUtils.getBooleanValue(this,SharedPreferencesUtils.LOGINSTATUS)){
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void initView() {
        homeFragment = new HomeFragment();
        replaceFragment(R.id.frame_home,homeFragment);

        btnHome = (LinearLayout) findViewById(R.id.btn_home);
        btnOrder = (LinearLayout) findViewById(R.id.btn_order);
        btnMine = (LinearLayout) findViewById(R.id.btn_mine);
        imgHome = (ImageView) findViewById(R.id.img_home);
        imgOrder = (ImageView) findViewById(R.id.img_home_order);
        imgMine = (ImageView) findViewById(R.id.img_home_mine);
        txtHome = (TextView) findViewById(R.id.txt_home);
        txtOrder = (TextView) findViewById(R.id.txt_home_order);
        txtMine = (TextView) findViewById(R.id.txt_home_mine);
    }

    private void setBtnNormal(){
        btnHome.setBackgroundColor(getResources().getColor(R.color.grey_normal));
        btnOrder.setBackgroundColor(getResources().getColor(R.color.grey_normal));
        btnMine.setBackgroundColor(getResources().getColor(R.color.grey_normal));
        imgHome.setImageResource(R.drawable.ic_home_normal);
        imgOrder.setImageResource(R.drawable.ic_order_normal);
        imgMine.setImageResource(R.drawable.ic_user_normal);
        txtHome.setTextColor(getResources().getColor(R.color.font_dark));
        txtOrder.setTextColor(getResources().getColor(R.color.font_dark));
        txtMine.setTextColor(getResources().getColor(R.color.font_dark));
    }

    private void setBtnPressed(View view){
        setBtnNormal();
        switch (view.getId()){
            case R.id.btn_home:
                btnHome.setBackgroundColor(getResources().getColor(R.color.grey_pressed));
                imgHome.setImageResource(R.drawable.ic_home_selected);
                txtHome.setTextColor(getResources().getColor(R.color.font_white));
                break;
            case R.id.btn_order:
                btnOrder.setBackgroundColor(getResources().getColor(R.color.grey_pressed));
                imgOrder.setImageResource(R.drawable.ic_order_selected);
                txtOrder.setTextColor(getResources().getColor(R.color.font_white));
                break;
            case R.id.btn_mine:
                btnMine.setBackgroundColor(getResources().getColor(R.color.grey_pressed));
                imgMine.setImageResource(R.drawable.ic_user_selected);
                txtMine.setTextColor(getResources().getColor(R.color.font_white));
                break;
        }
    }

    private void replaceFragment(int rId,android.app.Fragment fragment){
        android.app.FragmentManager fragmentManager = getFragmentManager();
        android.app.FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.replace(rId, fragment);
        beginTransaction.commit();
    }
    private OrderFragment orderFragment = null;
    private HomeFragment homeFragment = null;
    private MineFragment mineFragment = null;

    public void doClick(View view){
        switch (view.getId()) {
            case R.id.btn_home:
                setBtnPressed(view);
                if(homeFragment==null){
                    homeFragment = new HomeFragment();
                }
                replaceFragment(R.id.frame_home,homeFragment);
                break;
            case R.id.btn_order:
                setBtnPressed(view);
                if(orderFragment==null){
                    orderFragment = new OrderFragment();
                }
                replaceFragment(R.id.frame_home,orderFragment);
                break;
            case R.id.btn_mine:
                setBtnPressed(view);
                if(mineFragment==null){
                    mineFragment = new MineFragment();
                }
                replaceFragment(R.id.frame_home,mineFragment);
                break;
        }
    }

}
