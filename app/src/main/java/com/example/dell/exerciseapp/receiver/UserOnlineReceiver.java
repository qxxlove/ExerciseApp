package com.example.dell.exerciseapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import com.example.dell.exerciseapp.helper.ActivityControl;

/**
 * 强制用户下线广播
 */

public class UserOnlineReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("下线");
        builder.setMessage("当前用户已经登录，是否强制下线");
        builder.setCancelable(false);
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ActivityControl.finishAll();
            }
        });
        builder.show();
       // throw new UnsupportedOperationException("Not yet implemented");
    }
}
