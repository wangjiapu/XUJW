package com.example.com.xujw.Activtys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.com.xujw.Activtys.Utils.FormdataUtil;
import com.example.com.xujw.Activtys.Utils.JsoupService;
import com.example.com.xujw.R;

public class Me extends Activity implements View.OnClickListener{
    private LinearLayout person;//我的信息
    private LinearLayout plan;//培养计划
    private LinearLayout help;//意见与反馈
    private LinearLayout about;//关于我们
    private TextView outlogin;//退出登录

    private LinearLayout person2;
    private LinearLayout person1;
    private ImageView w;
    private TextView ww;
    private TextView myname,mynum;

    private ImageView mytouxiang;

    private TextView myname2,mynum2,myclass,mymajor,mycollege,mypolity,mykaohao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        initView();

    }



    private void initView() {
        person=(LinearLayout)findViewById(R.id.person);
        myname=(TextView)findViewById(R.id.myname);
        mynum=(TextView)findViewById(R.id.mynum);
        plan=(LinearLayout)findViewById(R.id.plan);
        help=(LinearLayout)findViewById(R.id.help);
        about=(LinearLayout)findViewById(R.id.about);
        outlogin=(TextView)findViewById(R.id.outlogin);
        person2=(LinearLayout)findViewById(R.id.layout4);
        person1=(LinearLayout)findViewById(R.id.layout1);
        w=(ImageView)findViewById(R.id.w);
        ww=(TextView)findViewById(R.id.ww);

        myname2=(TextView)findViewById(R.id.myname2);
        mynum2=(TextView)findViewById(R.id.mynum2);
        myclass=(TextView)findViewById(R.id.myclass);
        mymajor=(TextView)findViewById(R.id.mymajor);
        mycollege=(TextView)findViewById(R.id.mycollege);
        mypolity=(TextView)findViewById(R.id.mypolity);
        mykaohao=(TextView)findViewById(R.id.mykaohao);

        myname.setText(JsoupService.myname);
        mynum.setText("学号："+FormdataUtil.getUser());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.person:
                myname2.setText(JsoupService.person.get("姓名："));
                mynum2.setText(JsoupService.person.get("学号："));
                myclass.setText(JsoupService.person.get("行政班："));
                mymajor.setText(JsoupService.person.get("专业名称："));
                mycollege.setText(JsoupService.person.get("学院："));
                mypolity.setText(JsoupService.person.get("政治面貌："));
                mykaohao.setText(JsoupService.person.get("考生号："));
                person2.setVisibility(View.VISIBLE);
                person1.setVisibility(View.GONE);
                break;
            case R.id.help:
                Intent i=new Intent(Me.this,Activity_Plan.class);
                i.putExtra("data","2");
                Me.this.startActivity(i);
                break;
            case R.id.about:
                Intent i2=new Intent(Me.this,Activity_Plan.class);
                i2.putExtra("data","1");
                Me.this.startActivity(i2);
                break;
            case R.id.plan:
                Toast.makeText(this,"正在开发，敬请关注。。",Toast.LENGTH_SHORT).show();
                break;
            case R.id.outlogin:
                finish();
                break;

            case R.id.w:
            case R.id.ww:
                person1.setVisibility(View.VISIBLE);
                person2.setVisibility(View.GONE);
                break;

        }
    }

}
