package com.example.dell.exerciseapp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dell.exerciseapp.R;
import com.example.dell.exerciseapp.adapter.NumberAdaper;
import com.example.dell.exerciseapp.base.BaseActivity;
import com.example.dell.exerciseapp.bean.NumberBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 * 控件之RecyclerView 第一招
 *  ①  RecyclerView 的基本使用
 *
 *  ②  RecyclerView 进阶使用（上拉加载，下拉刷新，多布局等等）
 *
 *
 */


public class RecyclerViewStudyActivity extends BaseActivity {


    @BindView(R.id.recyclerView_one)
    RecyclerView recyclerViewOne;

    private List<NumberBean>  numberBeanList;
    private NumberAdaper numberAdaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_study);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        numberBeanList = new ArrayList<>();
        numberBeanList.add(new NumberBean("a","你好"));
        numberBeanList.add(new NumberBean("b","你好.."));
        numberBeanList.add(new NumberBean("c","你好..."));
        numberBeanList.add(new NumberBean("d","你好...."));
        numberBeanList.add(new NumberBean("e","你好....."));
        numberBeanList.add(new NumberBean("f","你好......"));
        numberBeanList.add(new NumberBean("g","你好........"));
        numberBeanList.add(new NumberBean("a","你好"));
        numberBeanList.add(new NumberBean("b","你好.."));
        numberBeanList.add(new NumberBean("c","你好..."));
        numberBeanList.add(new NumberBean("d","你好...."));
        numberBeanList.add(new NumberBean("e","你好....."));
        numberBeanList.add(new NumberBean("f","你好......"));
        numberBeanList.add(new NumberBean("g","你好........"));
        numberBeanList.add(new NumberBean("a","你好"));
        numberBeanList.add(new NumberBean("b","你好.."));
        numberBeanList.add(new NumberBean("c","你好..."));
        numberBeanList.add(new NumberBean("d","你好...."));
        numberBeanList.add(new NumberBean("e","你好....."));
        numberBeanList.add(new NumberBean("f","你好......"));
        numberBeanList.add(new NumberBean("g","你好........"));


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewOne.setLayoutManager(linearLayoutManager);
        numberAdaper = new NumberAdaper(numberBeanList);
        recyclerViewOne.setAdapter(numberAdaper);
    }
}
