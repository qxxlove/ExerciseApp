package com.example.dell.exerciseapp.helper;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2018/7/14.
 */

public class ActivityControl {

    private  static List<Activity>  activityList = new ArrayList<>();

    public  static  void addActivity(Activity activity){
        activityList.add(activity);
    }

    public  static  void removeActivity(Activity activity){
        activityList.remove(activity);
    }

    public  static  void finishAll(){
       for (Activity activity1: activityList){
           if (!activity1.isFinishing()){
               activity1.finish();
           }
       }
       activityList.clear();
    }




}
