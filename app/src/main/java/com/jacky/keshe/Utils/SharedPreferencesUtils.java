package com.jacky.keshe.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Jacky on 2017/1/7.
 */

public class SharedPreferencesUtils {
    private static String SPNAME = "keshe";
    public static final String LOGINSTATUS = "LoginStatus";

    public static void setValue(Context context,String key,String value){
        SharedPreferences preferences=context.getSharedPreferences(SPNAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getValue(Context context,String key){
        SharedPreferences preferences = context.getSharedPreferences(SPNAME, Context.MODE_PRIVATE);
        return preferences.getString(key,"defaultValue");
    }

    public static void setBooleanValue(Context context,String key,Boolean value){
        SharedPreferences preferences=context.getSharedPreferences(SPNAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static Boolean getBooleanValue(Context context,String key){
        SharedPreferences preferences = context.getSharedPreferences(SPNAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(key,false);
    }
}
