package com.example.com.xujw.Activtys.Utils;


import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.com.xujw.R;


public class MyDialog extends Dialog {
    public Dialog dialog;
    public TextView title, cancle, ok;


    private Spinner schooly_Spiner;
    private Spinner semester_Spiner;

    public static String schoolyear, semester;

    private Context context;

    public MyDialog(Context context) {
        super(context);
        this.context = context;
        init();
        initEvent();
    }

    private void init() {
        dialog = new Dialog(context, R.style.dialog);
        Window window=dialog.getWindow();
        window.setContentView(R.layout.class_dialog);
        dialog.setCancelable(false);
        title = (TextView) window.findViewById(R.id.tv_title);
        cancle = (TextView) window.findViewById(R.id.btn_cancel);
        ok = (TextView) window.findViewById(R.id.btn_ok);

        schooly_Spiner=(Spinner)window.findViewById(R.id.spinner1);
        semester_Spiner=(Spinner)window.findViewById(R.id.spinner2);
    }
    public String getSchoolYear(){
        schoolyear=schooly_Spiner.getSelectedItem().toString();

        return schoolyear;
    }
    public String getSemeater(){
        semester=semester_Spiner.getSelectedItem().toString();
        return semester;
    }

    private void initEvent() {
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesOnclickListener != null) {
                    yesOnclickListener.onYesClick();
                }
            }
        });
        //设置取消按钮被点击后，向外界提供监听
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noOnclickListener != null) {
                    noOnclickListener.onNoClick();
                }
            }
        });
    }

    //设置对话框标题
    public void setTitle(String titleStr) {
        title.setText(titleStr);
    }


    /**
     * 设置确定按钮和取消被点击的接口
     */
    public interface onYesOnclickListener {
        void onYesClick();
    }

    public interface onNoOnclickListener {
        void onNoClick();
    }

    /**
     * 设置取消按钮的显示内容和监听
     *
     */
    public void setNoOnclickListener(onNoOnclickListener onNoOnclickListener) {
        this.noOnclickListener = onNoOnclickListener;
    }

    /**
     * 设置确定按钮的显示内容和监听

     */
    public void setYesOnclickListener(onYesOnclickListener onYesOnclickListener) {
        this.yesOnclickListener = onYesOnclickListener;
    }

    private onNoOnclickListener noOnclickListener;//取消按钮被点击了的监听器
    private onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器


    public void getdismiss(){
        dialog.dismiss();
    }
}
