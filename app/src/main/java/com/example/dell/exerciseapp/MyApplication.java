package com.example.dell.exerciseapp;

import android.app.Application;

import tech.linjiang.pandora.Pandora;

/**
 * Created by DELL on 2018/8/25.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Pandora.get().open();
    }
}
