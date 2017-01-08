package com.jacky.keshe.Utils;

import android.os.AsyncTask;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by zfliu on 12/19/2016.
 */

public class HTTP {
    private static final String TAG_PHONE = "phone";
    private static final String TAG_PASSWORD = "password";

    private static final String host ="zfliu.5tangs.com";//或本地服务器192.168.191.1
    private static final int port = 80;
    private static String PathSegs1 = "kk/login";//或本地服务器keshe/login
    private static String PathSegs2 = "kk/register";//或本地服务器keshe/register
    public static final int login = 1;
    public static final int register =2;

    public static void Post(int action,String number,String text,final OnHttpStatusListener listener){
        //选择登录或注册动作
        final String path;
        switch (action){
            case login:
                path = PathSegs1;
                break;
            case register:
                path = PathSegs2;
                break;
            default:
                path = null;
        }

        String []params = {path,number,text};
        new AsyncTask<String,Void,String>() {

            @Override
            protected String doInBackground(String... objects) {
                return post(objects[0],objects[1],objects[2]);
            }

            @Override
            protected void onPostExecute(String s) {
                if (listener != null){
                    if (s == null){
                        listener.Error();
                    }else{
                        listener.Ok(s);
                    }
                }
            }
        }.execute(params);
    }
    private static String post(String path,String number,String text){
        if (path == null){
            return null;
        }
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add(TAG_PHONE, number)
                .add(TAG_PASSWORD, text)
                .build();
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host(host).port(port)
                .addPathSegments(path)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()){
                String ret = response.body().string();
                return ret;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static void Get(final String number, final OnHttpStatusListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                get(number,listener);
            }
        }).start();
    }
    private static void get(String number,OnHttpStatusListener listener){
        OkHttpClient client = new OkHttpClient();
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host(host).port(port)
                .addPathSegment("HelloWorld")
                .addPathSegment("login")
                .addQueryParameter(TAG_PHONE,number)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        if (listener == null){
            listener = new OnHttpStatusListener();
        }
        try {
            Response response = client.newCall(request).execute();
            String ret = response.body().string();
            listener.Ok(ret);
        } catch (Exception e) {
            e.printStackTrace();
            listener.Error();
        }
    }

    public static class OnHttpStatusListener {
        public void Ok(String text){}
        public void Error(){}
    }
}
