package com.example.dell.exerciseapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dell.exerciseapp.R;
import com.example.dell.exerciseapp.bean.PersonBean;

import java.util.List;

/**
 * Created by DELL on 2018/7/24.
 */

public class MListViewAdapter extends BaseAdapter {

    private Context context;
    private List<PersonBean> list;

    public MListViewAdapter(Context context, List<PersonBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_listview_db_layout,null);
            viewHolder = new ViewHolder(view);
             view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.textViewAge.setText(list.get(i).getAge()+"");
        viewHolder.textViewName.setText(list.get(i).getName());
        viewHolder.textViewId.setText(list.get(i).getId()+"");

        return view;
    }

    class  ViewHolder {
        private TextView textViewId;
        private TextView textViewName;
        private TextView textViewAge;

        public ViewHolder(View view) {
            textViewAge= view.findViewById(R.id.text_age);
            textViewId = view.findViewById(R.id.text_id);
            textViewName = view.findViewById(R.id.text_name);
        }
    }

}
