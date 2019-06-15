package com.example.dell.exerciseapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.dell.exerciseapp.utils.BaseUtils;


/**
 * 开机启动广播
 */

public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        BaseUtils.toast(context,"开机了");
       // throw new UnsupportedOperationException("Not yet implemented");
    }
}
