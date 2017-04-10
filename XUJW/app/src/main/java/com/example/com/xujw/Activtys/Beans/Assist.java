package com.example.com.xujw.Activtys.Beans;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 蒲家旺 on 2017/2/9.
 */
public class Assist {
    /**
     * 判断是否加载失败；
     */
    public static boolean loadClass_ok;
    public static boolean loadSore_ok;

    public static boolean paseTime() {

        Calendar date = Calendar.getInstance();
        if (date.get(Calendar.MONTH) + 1 < 6) {
            return true;
        }
        return false;
    }

    public static String urltime;
    public static String time;

    public static String pppp;

    /**
     * url中的汉字转码
     *
     * @param url
     * @return
     */

    public static String encodeUrl(String url) {
        String new_url = url;
        Matcher matcher = Pattern.compile("[\\u4e00-\\u9fa5]").matcher(new_url);
        while (matcher.find()) {
            try {
                new_url = new_url.replaceAll(matcher.group(), URLEncoder.encode(matcher.group(), "gb2312"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return new_url;

    }

    public static void saveUserInfo(Context context,String username) {
        /**
         * SharedPreferences将用户的数据存储到该包下的shared_prefs/config.xml文件中，
         * 并且设置该文件的读取方式为私有，即只有该软件自身可以访问该文件
         */
        SharedPreferences sPreferences = context.getSharedPreferences("config", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sPreferences.edit();
        editor.putString("username", username);
        editor.commit();
    }


}
