package com.example.com.xujw.Activtys;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.com.xujw.R;

public class Activity_Plan extends Activity implements View.OnClickListener {


    private LinearLayout layout2;
    private LinearLayout layout3;
    private ImageView back1, back3;
    private TextView back2, back4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        String data = getIntent().getStringExtra("data");


        layout2 = (LinearLayout) findViewById(R.id.layout2);
        layout3 = (LinearLayout) findViewById(R.id.layout3);

        back1 = (ImageView) findViewById(R.id.back21_layout1);
        back2 = (TextView) findViewById(R.id.back22_layout1);
        back3 = (ImageView) findViewById(R.id.back31_layout1);
        back4 = (TextView) findViewById(R.id.back32_layout1);

        if (data.equals("2")) {
            layout3.setVisibility(View.GONE);
            layout2.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back21_layout1:
            case R.id.back22_layout1:
                finish();
                break;
            case R.id.back31_layout1:
            case R.id.back32_layout1:
                finish();
                break;
        }
    }
}
