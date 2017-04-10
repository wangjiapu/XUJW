package com.example.com.xujw.Activtys;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.com.xujw.Activtys.Beans.Assist;
import com.example.com.xujw.Activtys.Utils.A_Class;
import com.example.com.xujw.Activtys.Utils.JsoupService;
import com.example.com.xujw.Activtys.Utils.MyDialog;
import com.example.com.xujw.R;


public class Class_Schedule extends Activity {
    private MyDialog myDialog;
    private TextView query_Dailog;
    private TextView cl11, cl12, cl13, cl14, cl15;
    private TextView cl21, cl22, cl23, cl24, cl25;
    private TextView cl31, cl32, cl33, cl34, cl35;
    private TextView cl41, cl42, cl43, cl44, cl45;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        initView();
        setView();

        query_Dailog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出设置对话框
                myDialog = new MyDialog(Class_Schedule.this);

                myDialog.setTitle("设置查询信息");//设置标题
                myDialog.setYesOnclickListener(new MyDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        A_Class.initClass2(JsoupService.linkMap.get("学生个人课表"),myDialog.getSchoolYear(),myDialog.getSemeater());
                        myDialog.getdismiss();

                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        setView();
                    }
                });
                myDialog.setNoOnclickListener(new MyDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        myDialog.getdismiss();
                    }
                });
                myDialog.dialog.show();//显示


            }
        });
    }


    private void initView() {
        query_Dailog=(TextView) findViewById(R.id.query);
        cl11 = (TextView) findViewById(R.id.cl11);
        cl31 = (TextView) findViewById(R.id.cl31);
        cl12 = (TextView) findViewById(R.id.cl12);
        cl32 = (TextView) findViewById(R.id.cl32);
        cl13 = (TextView) findViewById(R.id.cl13);
        cl33 = (TextView) findViewById(R.id.cl33);
        cl14 = (TextView) findViewById(R.id.cl14);
        cl34 = (TextView) findViewById(R.id.cl34);
        cl15 = (TextView) findViewById(R.id.cl15);
        cl35 = (TextView) findViewById(R.id.cl35);
        cl21 = (TextView) findViewById(R.id.cl21);
        cl41 = (TextView) findViewById(R.id.cl41);
        cl22 = (TextView) findViewById(R.id.cl22);
        cl42 = (TextView) findViewById(R.id.cl42);
        cl23 = (TextView) findViewById(R.id.cl23);
        cl43 = (TextView) findViewById(R.id.cl43);
        cl24 = (TextView) findViewById(R.id.cl24);
        cl44 = (TextView) findViewById(R.id.cl44);
        cl25 = (TextView) findViewById(R.id.cl25);
        cl45 = (TextView) findViewById(R.id.cl45);
    }
    private void setView() {

        if (Assist.loadClass_ok){
            cl11.setText(JsoupService.kechengbiao.get("0*0"));
            cl12.setText(JsoupService.kechengbiao.get("0*1"));
            cl13.setText(JsoupService.kechengbiao.get("0*2"));
            cl14.setText(JsoupService.kechengbiao.get("0*3"));
            cl15.setText(JsoupService.kechengbiao.get("0*4"));
            cl21.setText(JsoupService.kechengbiao.get("1*0"));
            cl22.setText(JsoupService.kechengbiao.get("1*1"));
            cl23.setText(JsoupService.kechengbiao.get("1*2"));
            cl24.setText(JsoupService.kechengbiao.get("1*3"));
            cl25.setText(JsoupService.kechengbiao.get("1*4"));
            cl31.setText(JsoupService.kechengbiao.get("2*0"));
            cl32.setText(JsoupService.kechengbiao.get("2*1"));
            cl33.setText(JsoupService.kechengbiao.get("2*2"));
            cl34.setText(JsoupService.kechengbiao.get("2*3"));
            cl35.setText(JsoupService.kechengbiao.get("2*4"));
            cl41.setText(JsoupService.kechengbiao.get("3*0"));
            cl42.setText(JsoupService.kechengbiao.get("3*1"));
            cl43.setText(JsoupService.kechengbiao.get("3*2"));
            cl44.setText(JsoupService.kechengbiao.get("3*3"));
            cl45.setText(JsoupService.kechengbiao.get("3*4"));

        }else {
            cl22.setText("谢");
            cl24.setText("谢");
            cl32.setText("惠");
            cl34.setText("顾");
        }
    }

}
