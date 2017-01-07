package com.jacky.keshe;

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
    private static String TAG_PHONE = "Number";
    private static String TAG_CONTENT = "Content";

    private static String host ="192.168.191.1";
    private static int port = 80;

    public static void Post(final String number, final String text, final OnHttpStatusListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                post(number,text,listener);
            }
        }).start();
    }
    private static void post(String number,String text,OnHttpStatusListener listener){
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add(TAG_PHONE, number)
                .add(TAG_CONTENT, text)
                .build();
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host(host).port(port)
                .addPathSegment("keshe")
                .addPathSegment("login")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        if (listener == null){
            listener = new OnHttpStatusListener();
        }
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()){
                String ret = response.body().string();
                listener.Ok(ret);
            }
        } catch (Exception e){
            listener.Error();
            e.printStackTrace();
        }
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
