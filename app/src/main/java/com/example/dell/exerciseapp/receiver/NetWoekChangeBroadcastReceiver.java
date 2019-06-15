package com.example.dell.exerciseapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.dell.exerciseapp.utils.BaseUtils;

/**
 * Created by DELL on 2018/7/12.
 */

public class NetWoekChangeBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager =
                ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()){
            BaseUtils.toast(context,"network is available");
        }else {
            BaseUtils.toast(context,"network is unavailable");
        }
    }
}
