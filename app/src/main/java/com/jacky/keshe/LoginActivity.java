package com.jacky.keshe;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class LoginActivity extends AppCompatActivity {

    private TextView textView;
    private EditText edt_login_user;
    private EditText edt_login_password;

    Handler handler = new Handler();
//    handler来处理多线程可以使用runnable接口，这里先定义该接口
//    线程中运行该接口的run函数
    Runnable update_thread = new Runnable() {
        @Override
        public void run() {
            //在线程运行的逻辑
            textView.append("\nupdate...");
            handler.postDelayed(update_thread,1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView(){
        textView = (TextView) findViewById(R.id.main_textView2);
        edt_login_user = (EditText) findViewById(R.id.edt_login_phone);
        edt_login_password = (EditText) findViewById(R.id.edt_login_password);
    }

    private void doLogin(){
        String phone = edt_login_user.getText().toString();
        String password = edt_login_password.getText().toString();
        if(phone.length()!=11||password.equals("")){
            Toast.makeText(this,"输入有误",Toast.LENGTH_SHORT).show();
            return;
        }
        HTTP.Post(phone,password,new HTTP.OnHttpStatusListener(){
            @Override
            public void Ok(final String text) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        JSONTokener taker = new JSONTokener(text);
                        String txt = "";
                        try {
                            JSONObject obj = (JSONObject) taker.nextValue();
                            txt = obj.getString("ret");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(getApplicationContext(),txt,Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void Error() {
                super.Error();
                Toast.makeText(getApplicationContext(),"请求登录失败",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void doClick(View view){
        Intent intent;
        switch(view.getId()){
            case R.id.main_startBtn:
                handler.post(update_thread);
                break;
            case R.id.main_stopBtn:
                handler.removeCallbacks(update_thread);
                textView.append("\nstop!");
                break;
            case R.id.imgvBack:
                finish();
                break;
            case R.id.main_tvZhuCe:
                intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.main_tvZhaoHui:
                intent = new Intent(LoginActivity.this,ForgetPasswdActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_login:
                doLogin();
                break;
        }
    }
}
