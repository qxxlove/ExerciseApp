package com.example.dell.exerciseapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.dell.exerciseapp.utils.BaseUtils;

/**
 * 我的第二个广播
 */

public class MyBroadCastTwoReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        BaseUtils.toast(context,"我是一个动态方式注册的广播");
        //throw new UnsupportedOperationException("Not yet implemented");
    }
}
