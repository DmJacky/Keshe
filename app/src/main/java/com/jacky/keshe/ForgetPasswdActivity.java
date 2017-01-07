package com.jacky.keshe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jacky.keshe.Utils.HTTP;

/**
 * Created by Jacky on 2016/12/28.
 */

public class ForgetPasswdActivity extends AppCompatActivity {
    private TextView tvHuoQu;
    private TextView tvYanZhenMa;
    private Button btnNext;
    private int time = 59;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0 ){
                tvHuoQu.setText(time+"");
                time--;
                if(time>=0){
                    handler.sendEmptyMessageDelayed(0,1000);
                }else{
                    tvHuoQu.setText("重新获取");
                }
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpasswd);

        initView();
    }

    private void initView() {
        tvHuoQu = (TextView) findViewById(R.id.forget_tvHuoQu);
        btnNext = (Button) findViewById(R.id.forget_btnNext);

    }

    public void doClick(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.forget_tvHuoQu:
                handler.sendEmptyMessage(0);
                time = 59;
                break;
            case R.id.forget_imgbBack:
                intent = new Intent(ForgetPasswdActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.forget_btnNext:
                Log.d("Next","next");
                HTTP.Get("123",new HTTP.OnHttpStatusListener(){
                    @Override
                    public void Ok(final String text) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                break;
        }
    }

}
