package com.example.com.xujw.Activtys.Utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.com.xujw.Activtys.Beans.CourseInfo;
import com.example.com.xujw.R;

import java.util.List;


/**
 * Created by 蒲家旺 on 2017/2/11.
 */
public class SoreAdapter extends BaseAdapter{
    private Context context;
    private List<CourseInfo> list;

    public SoreAdapter(Context context, List<CourseInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null){
            holder = new ViewHolder();
            //引入布局
            convertView = View.inflate(context, R.layout.sorelistitem, null);
            //实例化对象
            holder.Course_name=(TextView) convertView.findViewById(R.id.Course_name);
            holder.Course_nature = (TextView) convertView.findViewById(R.id.Course_nature);
            holder.Course_score = (TextView) convertView.findViewById(R.id.Course_score);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.Course_name.setText(String.valueOf(list.get(position).getCourse_name()));
        holder.Course_nature.setText(String.valueOf(list.get(position).getCourse_nature()));
        holder.Course_score.setText(String.valueOf(list.get(position).getCourse_score()));

        return convertView;
    }

    class ViewHolder {

        TextView Course_name;
        TextView Course_nature;
        TextView Course_score;
    }
}
