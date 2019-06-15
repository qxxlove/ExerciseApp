package com.example.dell.exerciseapp.base;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.dell.exerciseapp.helper.ActivityControl;
import com.example.dell.exerciseapp.receiver.UserOnlineReceiver;

/**
 * Created by DELL on 2018/7/14.
 */

public class BaseActivity extends AppCompatActivity {

    private UserOnlineReceiver userOnlineReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityControl.addActivity(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }


    /**
     * 好处： 保证只有再栈顶的Activity 才接收到此广播
     */
    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("UserOnlineReceiver");
        userOnlineReceiver =  new UserOnlineReceiver();
        registerReceiver(userOnlineReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (userOnlineReceiver != null){
            unregisterReceiver(userOnlineReceiver);
            userOnlineReceiver = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityControl.removeActivity(this);
    }
}
