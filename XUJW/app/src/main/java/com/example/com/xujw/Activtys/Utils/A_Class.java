package com.example.com.xujw.Activtys.Utils;



import android.util.Log;


import com.example.com.xujw.Activtys.Beans.Assist;

import java.io.IOException;
import java.util.Map;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class A_Class {



    public A_Class(){
    }


    public static void initClass(String url){
        String referer = HttpUtil.getSore_REFERER() + "?xh=" + FormdataUtil.getUser();

        Request requests = HttpUtil.getRequest(HttpUtil.getREFERER()+url, referer);
        Call callc = HttpUtil.getOkHttpClient().newCall(requests);
        callc.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200) {
                    Assist.loadClass_ok=true;
                    String content = new String(response.body().bytes(), "gb2312");
                     JsoupService.parseCourse(content);

                }
                else{
                   Assist.loadClass_ok=false;
                }
            }
        });

    }

    /**
     * dialog 更换学期，学年，刷新ui
     * @param url
     * @param year
     * @param semester
     */
    public static void initClass2(final String url, final String year, final String semester){

        String referer = HttpUtil.getSore_REFERER() + "?xh=" + FormdataUtil.getUser();
        Request requests = HttpUtil.getRequest(HttpUtil.getREFERER()+url, referer);
        Call callc = HttpUtil.getOkHttpClient().newCall(requests);
        callc.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200) {
                    String content = new String(response.body().bytes(), "gb2312");
                    Map<String, Object> classMap=JsoupService.getScoreYear(content);

                    RequestBody requestBody = new FormBody.Builder()
                            .add("__EVENTTARGET","xqd")
                            .add("__EVENTARGUMENT","")
                            .add("__VIEWSTATE",(String)classMap.get("__VIEWSTATE"))
                            .add("xnd",year)
                            .add("xqd",semester).build();
                    String referer =HttpUtil.getREFERER()+url;

                    Log.e("referer",referer);
                    Request requestc = HttpUtil.getRequest(referer, referer, requestBody);
                    call = HttpUtil.getOkHttpClient().newCall(requestc);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.code() == 200) {
                                Assist.loadClass_ok=true;
                                String content = new String(response.body().bytes(), "gb2312");
                                JsoupService.parseCourse(content);
                            }else{
                               Assist.loadClass_ok=false;
                            }
                        }
                    });
                }
                else{
                   Assist.loadClass_ok=false;
                }
            }
        });

    }


    /**
     * 初始化成绩单
     */

    public static void initSore(final String url, final String urltime,final String time, final String pp){
        final String referer=HttpUtil.getREFERER()+url;
        Request requests = HttpUtil.getRequest(referer, referer);
        Call calls = HttpUtil.getOkHttpClient().newCall(requests);
        calls.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200) {
                    String content = new String(response.body().bytes(), "gb2312");
                    Map<String, Object> classMap=JsoupService.getScoreYear(content);
                    RequestBody requestBody1 = new FormBody.Builder()
                            .add("__EVENTTARGET","xqd")
                            .add("__EVENTARGUMENT","")
                            .add("__VIEWSTATE",(String)classMap.get("__VIEWSTATE"))
                            .add("hidLanguage","")
                            .add("ddlXN",urltime)
                            .add("ddlXQ",time)
                            .add("ddl_kcxz",pp)
                            .add("btn_xq","").build();
                    Request request1 = HttpUtil.getRequest(referer, referer, requestBody1);


                    call = HttpUtil.getOkHttpClient().newCall(request1);

                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.code() == 200) {
                                Assist.loadSore_ok=true;
                                String content = new String(response.body().bytes(), "gb2312");
                                if (pp.equals("01")){
                                    JsoupService.parseSoreA(content);
                                }else{
                                    JsoupService.parseSoreB(content);
                                }

                            }else{
                               Assist.loadSore_ok=false;
                            }
                        }
                    });
                }
                else{
                    Assist.loadSore_ok=false;
                }
            }
        });

    }

    public static void initperson(String url){
        final String referer=HttpUtil.getSore_REFERER()+"?xh=" + FormdataUtil.getUser();
        Request requestp = HttpUtil.getRequest_person(HttpUtil.getREFERER()+url, referer);
        Call callp=HttpUtil.getOkHttpClient().newCall(requestp);
        callp.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code()==200){
                    String content = new String(response.body().bytes(), "gb2312");
                    JsoupService.parseperson(content);
                }
            }
        });
    }

}
