package com.example.dell.exerciseapp.bean;

/**
 * Created by DELL on 2018/7/10.
 */

public class NumberBean {


    private  String   name ;
    private  String   content ;

    public NumberBean(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
