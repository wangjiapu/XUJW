package com.example.com.xujw.Activtys.cookies;

import java.util.List;

import com.example.com.xujw.Activtys.LoginActivity;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by 蒲家旺 on 2017/2/4.
 */
public class CookiesManager implements CookieJar {

     private final PersistentCookieStore cookieStore =
             new PersistentCookieStore(LoginActivity.getApp());
    @Override
    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        if (list != null && list.size() > 0) {
            for (Cookie item : list) {
                cookieStore.add(httpUrl, item);
            }
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
        List<Cookie> cookies = cookieStore.get(httpUrl);
        return cookies;
    }
}
