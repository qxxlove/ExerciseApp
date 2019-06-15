package com.example.dell.exerciseapp.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by DELL on 2018/7/12.
 */

public class BaseUtils {


    public static   void   toast (Context context ,String content){
        Toast.makeText(context,content,Toast.LENGTH_LONG).show();
    }

}
