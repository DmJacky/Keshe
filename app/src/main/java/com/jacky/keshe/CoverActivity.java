package com.jacky.keshe;

import android.content.Intent;
import android.os.Bundle;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

/**
 * Created by Jacky on 2017/1/8.
 */

public class CoverActivity extends AppCompatActivity {
    private static final int delay_time = 1000;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover);

        getWindow().setFormat(PixelFormat.RGBA_8888);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //go to mainActivity,finish coverActivity
                Intent intent = new Intent(CoverActivity.this,HomepageActivity.class);
                CoverActivity.this.startActivity(intent);
                CoverActivity.this.finish();
            }
        },delay_time);
    }

}
