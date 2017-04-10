package com.example.com.xujw.Activtys.Utils;




import com.example.com.xujw.Activtys.cookies.CookiesManager;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by 蒲家旺 on 2017/1/15.
 */
public class HttpUtil {

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .cookieJar(new CookiesManager()).build();

    private static final String HOST="222.24.62.120";

    private static final String REFERER = "http://222.24.62.120/";

    private static final String CHECKCODE=REFERER+"CheckCode.aspx";

    private static final String URL_LOGIN=REFERER+"default2.aspx";

    private static final String Sore_REFERER=REFERER+"xs_main.aspx";

    /**
     * 验证码获取地址
     * @return
     */
    public static String getCHECKCODE() {
        return CHECKCODE;
    }

    /**
     * 获取验证码请求体
     * @param url
     * @return
     */
    public static Request getRequest(String url) {
        Request request = new Request.Builder().url(url)
                .addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .addHeader("Accept-Encoding","gzip, deflate, sdch")
                .addHeader("Accept-Language","zh-CN,zh;q=0.8")
                .addHeader("Connection","keep-alive")

                .addHeader("Host",HOST)
                .addHeader("Upgrade-Insecure-Requests","1")
                .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.75 Safari/537.36")
                .build();
        return request;
    }

    /**
     * 登录请求体
     * @param url
     * @param requestBody
     * @return
     */

    public static Request getRequest(String url, RequestBody requestBody){
        Request request=new Request.Builder().url(url)
                .addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .addHeader("Accept-Encoding","gzip, deflate, sdch")
                .addHeader("Accept-Language","zh-CN,zh;q=0.8")
                .addHeader("Connection","keep-alive")
                .addHeader("Host",HOST)
                .addHeader("Upgrade-Insecure-Requests","1")
                .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.75 Safari/537.36")
                .post(requestBody).build();
        return request;
    }

    /**
     * 课表post获取请求体
     * @param url
     * @param referer
     * @param requestBody
     * @return
     */
    public static Request getRequest(String url,String referer, RequestBody requestBody){
        Request request=new Request.Builder().url(url)
                .addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .addHeader("Accept-Encoding","gzip, deflate, sdch")
                .addHeader("Accept-Language","zh-CN,zh;q=0.8")
                .addHeader("Cache-Control","max-age=0")
                .addHeader("Connection","keep-alive")
                .addHeader("Content-Type","application/x-www-form-urlencoded")
                .addHeader("Host",HOST)
                .addHeader("Origin","http://222.24.62.120")
                .addHeader("Referer",referer)
                .addHeader("Upgrade-Insecure-Requests","1")
                .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.75 Safari/537.36")
                .post(requestBody).build();
        return request;
    }

    /**
     *  课表get获取请求体
     * @param url
     * @param referer
     * @return
     */
    public static Request getRequest(String url,String referer){
        Request request=new Request.Builder().url(url)
                .addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .addHeader("Accept-Encoding","gzip, deflate, sdch")
                .addHeader("Accept-Language","zh-CN,zh;q=0.8")
                .addHeader("Connection","keep-alive")
                .addHeader("Host",HOST)
                .addHeader("Referer",referer)
                .addHeader("Upgrade-Insecure-Requests","1")
                .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.75 Safari/537.36")
                .build();

        return request;
    }

    /**
     * 个人信息获取请求体
     * @param url
     * @param referer
     * @return
     */
    public static Request getRequest_person(String url,String referer){
        Request request=new Request.Builder().url(url)
                .addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .addHeader("Accept-Encoding","gzip, deflate, sdch")
                .addHeader("Accept-Language","zh-CN,zh;q=0.8")
                .addHeader("Connection","keep-alive")
                .addHeader("Host",HOST)
                .addHeader("Referer",referer)
                .addHeader("Upgrade-Insecure-Requests","1")
                .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.75 Safari/537.36")
                .build();
        return request;
    }


    public static String getURL_LOGIN(){
        return URL_LOGIN;
    }

    public static String getREFERER(){
        return REFERER;
    }

    public static String getSore_REFERER(){
        return Sore_REFERER;
    }
    public static OkHttpClient getOkHttpClient(){
        return okHttpClient;
    }


}
