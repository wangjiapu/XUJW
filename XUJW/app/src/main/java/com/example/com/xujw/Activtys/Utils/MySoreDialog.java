package com.example.com.xujw.Activtys.Utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.TextView;

import com.example.com.xujw.R;


/**
 * Created by 蒲家旺 on 2017/2/11.
 */
public class MySoreDialog{
    private Context context;


    private String Course_nature;//课程性质

    private String Course_belong;//课程归属

    private String Course_credit;//学分
    private String Course_make_up_score;//补考成绩
    private String Course_score;//成绩
    private String Course_average_point;//绩点
    private String Course_college;//开课学院
    public Dialog dialog;

    public TextView title;
    private TextView tv_nature,tv_credit,tv_make_up_score,tv_belong;
    private TextView tv_score,tv_average_point,tv_college;



    public MySoreDialog(Context context,String nature,String belong,String credit,
          String make_up_score,String score,String average_point,String college ) {
        this.context = context;
        this.Course_belong=belong;
        this.Course_college=college;
        this.Course_credit=credit;
        this.Course_make_up_score=make_up_score;
        this.Course_nature=nature;
        this.Course_score=score;
        this.Course_average_point=average_point;

        dialog = new Dialog(context, R.style.dialog);//Dialog的Style

        Window window = dialog.getWindow();
        //加载出你自己写出来的布局

        window.setContentView(R.layout.sore_dialog);//引用Dialog的布局
       // dialog.setCancelable(false);
        //后面的就很简单了。。。。
        title = (TextView) window.findViewById(R.id.sore_title);

        tv_nature = (TextView) window.findViewById(R.id.sore_nature);
        tv_nature.setText(Course_nature);
        tv_credit = (TextView) window.findViewById(R.id.sore_credit);
        tv_credit.setText(Course_credit);
        tv_make_up_score = (TextView) window.findViewById(R.id.sore_make_up_score);
        tv_make_up_score.setText(Course_make_up_score);
        tv_score = (TextView) window.findViewById(R.id.sore_score);
        tv_score.setText(Course_score);
        tv_average_point = (TextView) window.findViewById(R.id.sore_average_point);
        tv_average_point.setText(Course_average_point);
        tv_college = (TextView) window.findViewById(R.id.sore_college);
        tv_college.setText(Course_college);
        tv_belong = (TextView) window.findViewById(R.id.sore_belong);
        tv_belong.setText(Course_belong);

    }
    //设置对话框标题
    public void setTitle(String titleStr) {
        title.setText(titleStr);
    }

}
