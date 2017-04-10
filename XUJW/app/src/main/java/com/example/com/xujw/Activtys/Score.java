package com.example.com.xujw.Activtys;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.com.xujw.Activtys.Beans.Assist;
import com.example.com.xujw.Activtys.Beans.CourseInfo;
import com.example.com.xujw.Activtys.Utils.A_Class;
import com.example.com.xujw.Activtys.Utils.JsoupService;
import com.example.com.xujw.Activtys.Utils.MySoreDialog;
import com.example.com.xujw.Activtys.Utils.SoreAdapter;
import com.example.com.xujw.R;

import java.util.ArrayList;
import java.util.List;


public class Score extends Activity implements View.OnClickListener {
    private ListView list1;
    private ListView list2;
    private TextView error;
    private TextView more;

    private TextView bixiu, xuanxiu;
    private Boolean isfirst = true;

    private SoreAdapter soreAdapterlist1, soreAdapterlist2;
    private MySoreDialog mySoreDialog;

    private static String year,semeter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        list1 = (ListView) findViewById(R.id.sorelist);
        list2 = (ListView) findViewById(R.id.sorelist2);
        bixiu = (TextView) findViewById(R.id.bi);
        xuanxiu = (TextView) findViewById(R.id.xuan);
        more = (TextView) findViewById(R.id.query);
        year=Assist.urltime;semeter=Assist.time;
        refreshData();

        List<CourseInfo> temp = new ArrayList<>();
        CourseInfo courseInfo = new CourseInfo();
        courseInfo.setCourse_nature("");
        courseInfo.setCourse_score("成绩");
        courseInfo.setCourse_name("课程名称");
        temp.add(courseInfo);



        soreAdapterlist2 = new SoreAdapter(Score.this, temp);
        list2.setAdapter(soreAdapterlist2);

        bixiu.setOnClickListener(this);
        xuanxiu.setOnClickListener(this);
        more.setOnClickListener(this);
        IntentFilter intentFilter=new IntentFilter(More.ACTION);

        BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                soreAdapterlist1.notifyDataSetChanged();
                isfirst=true;

                soreAdapterlist2.notifyDataSetChanged();


            }
        };
        registerReceiver(broadcastReceiver,intentFilter);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bi:
                bixiu.setTextColor(Color.parseColor("#528adf"));
                bixiu.setBackgroundColor(Color.parseColor("#ffffff"));
                xuanxiu.setTextColor(Color.parseColor("#ffffff"));
                xuanxiu.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt));
                list2.setVisibility(View.GONE);
                list1.setVisibility(View.VISIBLE);
                break;
            case R.id.xuan:
                refreshData2();
                xuanxiu.setTextColor(Color.parseColor("#528adf"));
                xuanxiu.setBackgroundColor(Color.parseColor("#ffffff"));
                bixiu.setTextColor(Color.parseColor("#ffffff"));
                bixiu.setBackgroundDrawable(getResources().getDrawable(R.drawable.bt));
                list1.setVisibility(View.GONE);
                list2.setVisibility(View.VISIBLE);
                break;
            case R.id.query:
                Intent intent = new Intent(Score.this, More.class);
                Score.this.startActivity(intent);
                break;
        }
    }

    //继承OnItemClickListener，当子项目被点击的时候触发
    private final class ItemClickEvent implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CourseInfo courseInfo = JsoupService.chengjidan.get(position);
                mySoreDialog = new MySoreDialog(Score.this, courseInfo.getCourse_nature(),
                        courseInfo.getCourse_belong(), courseInfo.getCourse_credit(),
                        courseInfo.getCourse_make_up_score(),
                        courseInfo.getCourse_score(),
                        courseInfo.getCourse_average_point(),
                        courseInfo.getCourse_college()
                );
                mySoreDialog.setTitle(JsoupService.chengjidan.get(position).getCourse_name());//设置标题
                mySoreDialog.dialog.show();//显示
        }
    }

    private final class ItemClickEvent2 implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            CourseInfo courseInfo = JsoupService.chengjidan2.get(position);
            mySoreDialog = new MySoreDialog(Score.this, courseInfo.getCourse_nature(),
                    courseInfo.getCourse_belong(), courseInfo.getCourse_credit(),
                    courseInfo.getCourse_make_up_score(),
                    courseInfo.getCourse_score(),
                    courseInfo.getCourse_average_point(),
                    courseInfo.getCourse_college()
            );
            mySoreDialog.setTitle(JsoupService.chengjidan2.get(position).getCourse_name());//设置标题
            mySoreDialog.dialog.show();//显示

        }
    }

    private void refreshData() {
        if (Assist.loadSore_ok) {
            if (JsoupService.chengjidan.size() > 0) {
                soreAdapterlist1 = new SoreAdapter(Score.this, JsoupService.chengjidan);
                list1.setAdapter(soreAdapterlist1);
                list1.setOnItemClickListener(new ItemClickEvent());
            } else {
                error = (TextView) findViewById(R.id.error);
                list1.setVisibility(View.GONE);
                error.setText("还未有成绩，请耐心等待.....");
                error.setVisibility(View.VISIBLE);
            }

        } else {
            list1.setVisibility(View.GONE);
            error = (TextView) findViewById(R.id.error);
            error.setVisibility(View.VISIBLE);
        }

    }
    private void refreshData2() {
        if (isfirst) {
            if (JsoupService.chengjidan2.size()<0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            soreAdapterlist2 = new SoreAdapter(Score.this, JsoupService.chengjidan2);
            list2.setAdapter(soreAdapterlist2);
            list2.setOnItemClickListener(new ItemClickEvent2());
            isfirst=false;
        }
    }


}
