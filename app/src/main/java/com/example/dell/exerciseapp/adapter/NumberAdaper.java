package com.example.dell.exerciseapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.exerciseapp.R;
import com.example.dell.exerciseapp.bean.NumberBean;

import java.util.List;

/**
 * Created by DELL on 2018/7/10.
 */

public class NumberAdaper extends RecyclerView.Adapter<NumberAdaper.ViewHolder> {

    private List<NumberBean>  numberBeanList;

    public NumberAdaper(List<NumberBean> numberBeanList) {
        this.numberBeanList = numberBeanList;
    }

    class  ViewHolder extends  RecyclerView.ViewHolder{
         TextView  textViewName;
         TextView  textViewConetent;

        public ViewHolder(View itemView) {
            super(itemView);
           textViewName =  itemView.findViewById(R.id.text_name);
           textViewConetent=   itemView.findViewById(R.id.text_content);
        }
    }

    @Override
    public NumberAdaper.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NumberAdaper.ViewHolder holder, int position) {
        holder.textViewName.setText(numberBeanList.get(position).getName());
        holder.textViewConetent.setText(numberBeanList.get(position).getContent());

    }

    @Override
    public int getItemCount() {
        return numberBeanList.size();
    }



}
