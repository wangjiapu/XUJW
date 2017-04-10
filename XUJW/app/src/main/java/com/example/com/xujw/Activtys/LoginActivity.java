package com.example.com.xujw.Activtys;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.com.xujw.Activtys.Beans.Assist;
import com.example.com.xujw.Activtys.Utils.A_Class;
import com.example.com.xujw.Activtys.Utils.FormdataUtil;
import com.example.com.xujw.Activtys.Utils.HttpUtil;
import com.example.com.xujw.Activtys.Utils.JsoupService;
import com.example.com.xujw.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends Activity implements View.OnClickListener {
    private EditText username;
    private EditText password;
    private EditText pass_code;
    private SharedPreferences sp;
    private SharedPreferences.Editor et;
    private Button login;

    private LinearLayout logining,loginok;

    public static Application app;

    public static Application getApp() {
        return app;
    }

    //验证码图片
    private ImageView imageView_code;

    private boolean isGetCheckCode;

    private Handler handler;
    private int year = Calendar.getInstance().get(Calendar.YEAR);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        //浸入式状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);



        app = getApplication();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.obj != null && msg.obj instanceof Bitmap) {
                    Bitmap bitmap = (Bitmap) msg.obj;
                    imageView_code.setImageBitmap(bitmap);
                } else if (msg.obj != null && msg.obj instanceof String) {
                    Toast.makeText(LoginActivity.this, (String)
                            msg.obj, Toast.LENGTH_SHORT).show();
                }
                if (msg.what==1){
                    loginok.setVisibility(View.VISIBLE);
                    logining.setVisibility(View.GONE);
                }
            }
        };

        if (Assist.paseTime()) {   //如果时间为上半年那么就返回真
            Assist.urltime = year - 1 + "-" + year;
            Assist.time = "1";
            Assist.pppp=year - 1 + "-" + year+"年  第1学期";
        } else {
            Assist.urltime = year + "-" + year + 1;
            Assist.time = "2";
            Assist.pppp=year - 1 + "-" + year+"年  第2学期";
        }
        //初始化控件
        initView();
        //加载验证码
        logining.setVisibility(View.VISIBLE);
        getCheckCode();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_ok();
            }
        });
    }

    /**
     * 加载验证码
     */
    private void getCheckCode() {
        //如果正在加载，直接返回；
        if (isGetCheckCode) {
            return;
        }
        isGetCheckCode = true;

        try {
            Request request = HttpUtil.getRequest(HttpUtil.getCHECKCODE());
            HttpUtil.getOkHttpClient().newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Message message = Message.obtain();
                    message.obj = BitmapFactory.decodeResource(getResources(), R.drawable.code_error);
                    handler.sendMessage(message);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Message message = Message.obtain();
                    if (response.code() == 200) {
                        message.what=1;
                        message.obj = BitmapFactory.decodeStream(response.body().byteStream());
                    } else {

                        message.obj = BitmapFactory.decodeResource(getResources(), R.drawable.code_error);
                    }
                    handler.sendMessage(message);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //加载验证码结束
            isGetCheckCode = false;
        }
    }

    /**
     * 登录
     */
    private void login_ok() {

        if (username.getText().toString().isEmpty()) {
            Toast.makeText(LoginActivity.this, "用户名为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.getText().toString().isEmpty()) {
            Toast.makeText(LoginActivity.this, "请输入密码！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pass_code.getText().toString().isEmpty()) {
            Toast.makeText(LoginActivity.this, "验证码为空！", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestBody requestBody = FormdataUtil.requestBody_L(username.getText().toString().trim(),
                password.getText().toString().trim(),
                pass_code.getText().toString().trim());
        Request request = HttpUtil.getRequest(HttpUtil.getURL_LOGIN(), requestBody);

        Call call = HttpUtil.getOkHttpClient().newCall(request);
        final Message message = Message.obtain();
        Toast.makeText(LoginActivity.this, "加载中，请稍后。。。", Toast.LENGTH_LONG).show();

        //强制软键盘隐藏
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                message.obj = "登录失败！";
                handler.sendMessage(message);
                //加载验证码
                getCheckCode();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200) {
                    Assist.saveUserInfo(LoginActivity.this, username.getText().toString());

                    String content = new String(response.body().bytes(), "gb2312");

                    if (JsoupService.isLogin(content) != null) {

                        Intent intent = new Intent();
                        JsoupService.parseMenu(content);
                        A_Class.initClass(JsoupService.linkMap.get("学生个人课表"));
                        A_Class.initSore(JsoupService.linkMap.get("成绩查询"), Assist.urltime, Assist.time, "01");
                        intent.setClass(LoginActivity.this, MainActivity.class);
                        LoginActivity.this.startActivity(intent);
                    } else {
                        /*
                            不能在非ui线程中更新ui
                        Toast.makeText(MainActivity.this,"账号或密码错误",
                                Toast.LENGTH_SHORT).show();*/
                        message.obj = "账号或密码错误";
                        handler.sendMessage(message);
                        //加载验证码
                        getCheckCode();
                    }

                }
            }
        });
    }


    private void initView() {
        SharedPreferences sPreferences = getSharedPreferences("config", MODE_PRIVATE);
        String use = sPreferences.getString("username", "");
       // String pass=sPreferences.getString("pass","");
        username = (EditText) findViewById(R.id.username);
        username.setText(use);
        password = (EditText) findViewById(R.id.password);
       // password.setText(pass);
        pass_code = (EditText) findViewById(R.id.pass_code);

        login = (Button) findViewById(R.id.login);

        loginok=(LinearLayout)findViewById(R.id.loginok);
        logining=(LinearLayout)findViewById(R.id.logining);

        imageView_code = (ImageView) findViewById(R.id.imageView_code);
        imageView_code.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imageView_code) {
            getCheckCode();
        }
    }

}
