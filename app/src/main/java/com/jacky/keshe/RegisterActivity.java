package com.jacky.keshe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jacky on 2016/12/28.
 */

public class RegisterActivity extends AppCompatActivity {
    private TextView tvHuoQu;
    private CheckBox checkBox;
    private Button btnZhuCe;
    private EditText edtPhone;
    private EditText edtPassword;
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
                    if(!tvHuoQu.isClickable()){
                        tvHuoQu.setClickable(true);
                        tvHuoQu.setText("重新获取");
                        time = 59;
                    }
                }
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        initView();
    }

    private void initView() {
        tvHuoQu = (TextView) findViewById(R.id.register_tvHuoQu);
        checkBox = (CheckBox) findViewById(R.id.register_checkBox);
        btnZhuCe = (Button) findViewById(R.id.register_btnZhuCe);
        edtPhone = (EditText) findViewById(R.id.edt_register_phone);
        edtPassword = (EditText) findViewById(R.id.edt_login_password);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    btnZhuCe.setBackground(getResources().getDrawable(R.drawable.btn_orange_normal));
                    btnZhuCe.setEnabled(true);
                }else{
                    btnZhuCe.setBackground(getResources().getDrawable(R.drawable.btn_gray_normal));
                    btnZhuCe.setEnabled(false);
                }
            }
        });
    }

    private void doRegister(){
        String phone = edtPhone.getText().toString();
        String password = edtPassword.getText().toString();
        if (phone.length() != 11 || password.equals("")) {
            Toast.makeText(this, "输入有误", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    public void doClick(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.register_tvHuoQu:
                if(tvHuoQu.isClickable()){
                    handler.sendEmptyMessage(0);
                    tvHuoQu.setClickable(false);
                }
                break;
            case R.id.register_imgbBack:
                intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.register_btnZhuCe:
                Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
