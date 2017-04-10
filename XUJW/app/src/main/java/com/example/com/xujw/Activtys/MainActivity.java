package com.example.com.xujw.Activtys;

import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TabHost;


import com.example.com.xujw.Activtys.Beans.Assist;
import com.example.com.xujw.Activtys.Utils.A_Class;
import com.example.com.xujw.Activtys.Utils.JsoupService;
import com.example.com.xujw.R;

import java.util.Map;


public class MainActivity extends TabActivity implements TabHost.OnTabChangeListener {
    private TabHost myTabHost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        initTabs();//初始化选项卡
    }

    private void initTabs() {

        myTabHost = getTabHost();
        //如果您使用的是setContent(android.content.Intent),这必须被称为自activityGroup需要启动当地的活动
        myTabHost.setup(this.getLocalActivityManager());
        //添加文件管理选项卡
        myTabHost.addTab(myTabHost.newTabSpec("tab_class").setIndicator("课表").setContent(
                new Intent(this, Class_Schedule.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
        myTabHost.addTab(myTabHost.newTabSpec("tab_score").setIndicator("成绩").setContent(
                new Intent(this, Score.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));

        myTabHost.addTab(myTabHost.newTabSpec("tab_me").setIndicator("我").setContent(
                new Intent(this, Me.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));

        myTabHost.setCurrentTab(1);
        myTabHost.setOnTabChangedListener(this);
        A_Class.initperson(JsoupService.linkMap.get("个人信息"));
        A_Class.initSore(JsoupService.linkMap.get("成绩查询"), Assist.urltime, Assist.time, "10");
    }

    @Override
    public void onTabChanged(String tabId) {

    }
}
