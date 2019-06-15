package com.example.dell.exerciseapp.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.TextView;

import com.example.dell.exerciseapp.R;
import com.example.dell.exerciseapp.base.BaseActivity;
import com.example.dell.exerciseapp.receiver.MyBroadCastTwoReceiver;
import com.example.dell.exerciseapp.receiver.NetWoekChangeBroadcastReceiver;
import com.example.dell.exerciseapp.utils.BaseUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 * 工具之 BroadCast 第一招
 *
 * 广播的实现
 *
 * 注意： 在我们的应用程序中使用的广播，其他的应用程序是可以接收到的
 *        所以才出现：
 *              标准广播： 不做人何特殊处理，满足大部分需求。
 *              有序广播： 可设置优先级，拦截的特点
 *              系统广播： 只局限于自己的APP
 *              根据我们的需求，如果不想其他界面收到我们的广播，
 *                   我们可以在合适的时候进行拦截，其他的界面就收不到这个广播了
 *        当然： 系统处于安全考虑。提供了本地广播（只局限于自己的APP内部）
 *
 *         写法：
 *              静态注册： APP 不启动也可以收到广播
 *              动态注册    APP 启动才可以收到广播
 *
 *         注意： Android 8.0 以后 应该禁止了静态广播的注册，必须使用动态注册方可解决
 *
 */

public class BroadCastActivity extends BaseActivity {

    @BindView(R.id.text_send_one)
    TextView textSendOne;
    @BindView(R.id.text_send_two)
    TextView textSendTwo;

    private IntentFilter intentFilter;
    /** 网络广播*/
    private NetWoekChangeBroadcastReceiver netWoekChangeBroadcastReceiver;
    /** 动态广播*/
    private MyBroadCastTwoReceiver myBroadCastTwoReceiver;
    /** 本地广播*/
    private LocalBroadcastManager localBroadcastManager;
    private MyLocalBroadCast myLocalBroadCast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast);
        ButterKnife.bind(this);
        initBrocast();
    }

    @OnClick({R.id.text_send_one,R.id.text_send_two,R.id.text_send_three,
            R.id.text_send_four})
    public  void  initClick  (View view){
        switch (view.getId()) {
            /**静态注册的广播*/
            case R.id.text_send_one:
                Intent intent = new Intent();
                intent.setAction("com.example.dell.exerciseapp.receiver.MyBroadCastReceiver");
                sendBroadcast(intent);
                break;
                /**动态方式注册的广播*/
            case R.id.text_send_two:
                Intent intent1 = new Intent();
                intent1.setAction("MyBroadCastTwoReceiver");
                sendBroadcast(intent1);
                break;
                /**发送本地广播*/
            case R.id.text_send_three:
                localBroadcastManager.sendBroadcast(new Intent("MyLocalBroadCast"));
                break;
                /**发送用户下线广播*/
            case R.id.text_send_four:
                sendBroadcast(new Intent("UserOnlineReceiver"));
                break;
            default:
        }
    }



    /**
     * 注册广播
     */
    private void initBrocast() {
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        netWoekChangeBroadcastReceiver = new NetWoekChangeBroadcastReceiver();
        registerReceiver(netWoekChangeBroadcastReceiver, intentFilter);

        intentFilter = new IntentFilter();
        intentFilter.addAction("MyBroadCastTwoReceiver");
        myBroadCastTwoReceiver = new MyBroadCastTwoReceiver();
        registerReceiver(myBroadCastTwoReceiver,intentFilter);

        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        intentFilter = new IntentFilter();
        intentFilter.addAction("MyLocalBroadCast");
        myLocalBroadCast = new MyLocalBroadCast();
        localBroadcastManager.registerReceiver(myLocalBroadCast,intentFilter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(netWoekChangeBroadcastReceiver);
        unregisterReceiver(myBroadCastTwoReceiver);
        localBroadcastManager.unregisterReceiver(myLocalBroadCast);
    }


    public  class  MyLocalBroadCast extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            BaseUtils.toast(context,"本地广播,其他APP 收不到");
        }
    }
}
