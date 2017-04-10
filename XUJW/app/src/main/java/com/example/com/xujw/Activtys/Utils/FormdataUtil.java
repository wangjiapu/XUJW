package com.example.com.xujw.Activtys.Utils;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * Created by 蒲家旺 on 2017/2/6.
 */
public class FormdataUtil {

    private static RequestBody requestBody;
    private static String  user;



    public static String getUser(){
        return user;
    }
    /**
     *  登录的请求参数
     * @param username
     * @param password
     * @param pass_code
     * @return
     */
    public static RequestBody requestBody_L(String username,String password,String pass_code){
        user=username;
        return requestBody=new FormBody.Builder()
                .add("__VIEWSTATE","dDwtNTE2MjI4MTQ7Oz5O9kSeYykjfN0r53Yqhqckbvd83A==")
                .add("txtUserName",username)
                .add("Textbox1","")
                .add("Textbox2",password)
                .add("txtSecretCode",pass_code)
                .add("RadioButtonList1","")
                .add("Button1","")
                .add("lbLanguage","")
                .add("hidPdrs","")
                .add("hidsc","")
                .build();
    }

}
