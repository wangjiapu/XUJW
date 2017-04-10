package com.example.com.xujw.Activtys;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.com.xujw.Activtys.Beans.Assist;
import com.example.com.xujw.Activtys.Beans.CourseInfo;
import com.example.com.xujw.Activtys.Utils.A_Class;
import com.example.com.xujw.Activtys.Utils.JsoupService;
import com.example.com.xujw.Activtys.Utils.MyExpandableAdapter;
import com.example.com.xujw.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class More extends Activity implements View.OnClickListener {


    public static String ACTION="ABCDEFGHI";
    private ExpandableListView expandableListView1;
    private ExpandableListView expandableListView2;

    private MyExpandableAdapter myExpandableAdapter1;
    private MyExpandableAdapter myExpandableAdapter2;

    private InputMethodManager imm;
    /**
     * 学期信息
     */
    private List<String> list1;
    private List<List<String>> list2;
    /**
     * 其他课得信息
     */
    private List<String> list3;
    private List<List<String>> list4;


    private ImageView back;
    private TextView back1;

    private EditText search;
    private EditText search2;
    private TextView tv, tvp;
    private Button bt;
    private LinearLayout linearLayout1;
    private LinearLayout linearLayout2;
    private RelativeLayout relativeLayout;

    /**
     * 查询时得到的课表详细信息
     */

    private TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7;
    private TextView pjw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        initView();
        initEvent();
    }


    private void initView() {
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        list4 = new ArrayList<>();
        //adapterlist = new ArrayList<>();

        List<String> templist = new ArrayList<>();
        List<String> qqq = new ArrayList<>();
        back = (ImageView) findViewById(R.id.back);
        back1 = (TextView) findViewById(R.id.back1);
        search = (EditText) findViewById(R.id.search);
        search2 = (EditText) findViewById(R.id.search2);
        tv = (TextView) findViewById(R.id.tvw);
        tv.setText(Assist.pppp);
        bt = (Button) findViewById(R.id.cancel_search);
        tvp = (TextView) findViewById(R.id.tvp);
        linearLayout1 = (LinearLayout) findViewById(R.id.linear1);
        linearLayout2 = (LinearLayout) findViewById(R.id.linear2);
        relativeLayout = (RelativeLayout) findViewById(R.id.relatve);

        tv1 = (TextView) findViewById(R.id.name);
        tv2 = (TextView) findViewById(R.id.chengji2);
        tv3 = (TextView) findViewById(R.id.xuefeng2);
        tv4 = (TextView) findViewById(R.id.jidian2);
        tv5 = (TextView) findViewById(R.id.bukao2);
        tv6 = (TextView) findViewById(R.id.guishu2);
        tv7 = (TextView) findViewById(R.id.xingzhi2);
        pjw=(TextView)findViewById(R.id.pjw);


        expandableListView1 = (ExpandableListView) findViewById(R.id.expandablelistview1);
        expandableListView2 = (ExpandableListView) findViewById(R.id.expandablelistview2);
        list1.add("学期列表");

        int now=Integer.parseInt(JsoupService.person.get("当前所在级："));
        int temp= Calendar.getInstance().get(Calendar.YEAR)-now;
        for (int i=0;i<temp;i++){
            templist.add((now+i)+"-"+(now+i+1)+"年  第1学期");
            if (i==temp-1){
                break;
            }
            templist.add((now+i)+"-"+(now+i+1)+"年  第2学期");
        }
        list2.add(templist);

        list3.add("限选课");
        list3.add("辅修课");
        list3.add("课外实践教学");
        list3.add("素质拓展");
        list3.add("跨学科");
        list3.add("人文素质限选");
        /**
         * 这个也可以从学校官网获取，但是现在就这样吧；
         */
        qqq.add("无成绩");
        for (int index = 0; index < list3.size(); ++index) {
            list4.add(qqq);
        }

    }

    private void initEvent() {
        back.setOnClickListener(this);
        back1.setOnClickListener(this);
        bt.setOnClickListener(this);

        myExpandableAdapter1 = new MyExpandableAdapter(More.this, list1, list2);
        expandableListView1.setAdapter(myExpandableAdapter1);
        myExpandableAdapter2 = new MyExpandableAdapter(More.this, list3, list4);
        expandableListView2.setAdapter(myExpandableAdapter2);



        expandableListView1.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {


                String year=list2.get(groupPosition).get(childPosition).substring(0,9);
                String semeter=list2.get(groupPosition).get(childPosition).substring(13,14);

                A_Class.initSore(JsoupService.linkMap.get("成绩查询"), year, semeter, "01");
                A_Class.initSore(JsoupService.linkMap.get("成绩查询"), year, semeter, "10");
                Assist.pppp=list2.get(groupPosition).get(childPosition);
                pjw.setVisibility(View.VISIBLE);
                final ProgressDialog progressDialog = new ProgressDialog(More.this);
                progressDialog.setMessage("加载中...");
                progressDialog.setCancelable(false);

                progressDialog.show();
                try {
                    Thread.sleep(4500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(ACTION);
                sendBroadcast(intent);
                pjw.setVisibility(View.GONE);
                finish();
                return true;
            }
        });
        search.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //获取焦点
                    linearLayout1.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.VISIBLE);
                    search2.requestFocus();
                    search2.setFocusable(true);
                    search2.setFocusableInTouchMode(true);
                    //强制弹出软件盘
                    imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
                    search2.setOnKeyListener(new View.OnKeyListener() {

                        @Override
                        public boolean onKey(View v, int keyCode, KeyEvent event) {
                            if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {

                                CourseInfo courseInfo123 = updata(search2.getText().toString().trim());
                                if (courseInfo123 != null) {
                                    /**
                                     * 打开课程详细信息
                                     *
                                     */
                                    tv1.setText(courseInfo123.getCourse_name());
                                    tv2.setText(courseInfo123.getCourse_score());
                                    tv3.setText(courseInfo123.getCourse_credit());
                                    tv4.setText(courseInfo123.getCourse_average_point());
                                    tv5.setText(courseInfo123.getCourse_make_up_score());
                                    tv6.setText(courseInfo123.getCourse_college());
                                    tv7.setText(courseInfo123.getCourse_nature());
                                    relativeLayout.setVisibility(View.VISIBLE);
                                    search2.setText("");
                                } else {
                                    tvp.setVisibility(View.VISIBLE);
                                    relativeLayout.setVisibility(View.GONE);
                                }
                            }
                            if (event.getKeyCode()== KeyEvent.KEYCODE_DEL && event.getAction()==KeyEvent.ACTION_DOWN) {
                                tvp.setVisibility(View.GONE);
                                relativeLayout.setVisibility(View.GONE);

                            }
                            return false;
                        }
                    });
                }
            }
        });

    }

    private CourseInfo updata(String s) {

        int strlen = s.length();
        CourseInfo course1;

        for (int i = 0; i < JsoupService.chengjidan2.size(); i++) {
            String pp = JsoupService.chengjidan2.get(i).getCourse_name();
            if (s.equals(pp.substring(0, strlen))) {
               course1 = JsoupService.chengjidan2.get(i);
                return course1;
            }
        }

        for (int j = 0; j < JsoupService.chengjidan.size(); j++) {
            String pp = JsoupService.chengjidan.get(j).getCourse_name();
            Log.e("2"+j,pp);
            if (s.equals(pp.substring(0, strlen))) {
                CourseInfo course = JsoupService.chengjidan.get(j);
                return course;
            }
        }


        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
            case R.id.back1:
                finish();
                break;
            case R.id.cancel_search:
                linearLayout2.setVisibility(View.GONE);
                linearLayout1.setVisibility(View.VISIBLE);
                break;

        }
    }
}
