package com.example.com.xujw.Activtys.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.com.xujw.R;

import java.util.List;

public class MyExpandableAdapter extends BaseExpandableListAdapter {
    private List<String> groupArray;
    private List<List<String>> childArray;
    private Context mContext;

    public MyExpandableAdapter(Context context,List<String> groupArray,List<List<String>> childArray){
        this.mContext=context;
        this.groupArray=groupArray;
        this.childArray=childArray;

    }

    @Override
    public int getGroupCount() {
        return groupArray.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childArray.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupArray.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childArray.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view=convertView;
        GroupHolder groupHolder=null;
        if (view==null) {
            groupHolder = new GroupHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.year_and_semester_item, null);
            groupHolder.moreSemester = (TextView) view.findViewById(R.id.mingzi);
            view.setTag(groupHolder);
        }else{
            groupHolder=(GroupHolder)view.getTag();
        }

        groupHolder.moreSemester.setText(groupArray.get(groupPosition));

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = convertView;
        ChildHolder holder = null;
        if(view == null){
            holder = new ChildHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.y_a_s_child, null);
            holder.moretime = (TextView)view.findViewById(R.id.tv_child_name);
            view.setTag(holder);
        }else{
            holder = (ChildHolder)view.getTag();
        }
        holder.moretime.setText(childArray.get(groupPosition).get(childPosition));

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupHolder{
        TextView moreSemester;
    }
    class ChildHolder{
        TextView moretime;
    }

}
