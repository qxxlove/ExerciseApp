package com.example.dell.exerciseapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

import com.example.dell.exerciseapp.R;
import com.example.dell.exerciseapp.base.BaseActivity;
import com.example.dell.exerciseapp.fargment.RightFragement;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 *
 * 布局之Fragment 第一招
 *
 *  场景： 当我们开发一个应用 既要使用手机 又要适应 平板
 *         此时我们需要开发两套布局，我们判断当前设备是手机还是平板，
 *           加载对应的布局即可。
 *           那么如何区分当前设备是手机还是平板呢？
 *           我们采取设备屏幕的宽度进行判断，因为手机宽小，平板宽大
 *
 *   思考： ViewPager 中 Fragment 懒加载实践
 *
 *          Activity 是如何加载Fragment
 *
 *          Fragment 管理机制，及任务栈
 *
 *          常用的hide,show，replace 方法
 *
 */


public class FragmentActivity extends BaseActivity {


    @BindView(R.id.frameLayout_one)
    FrameLayout frameLayoutOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.fragment_left})
    public  void  initclick (View view){
        switch (view.getId()){
            case R.id.fragment_left:
                 replaceFragment(new RightFragement());
                break;
            default:
        }
    }

    public  void  replaceFragment (Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout_one,fragment);
         /**模拟返回栈*/
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}
/*
        打开当前Activity
        D/LeftFragement: onAttach()
        D/LeftFragement: onCreate()
        D/LeftFragement: onCreateView()
        D/LeftFragement: onActivityCreated()
        D/LeftFragement: onStart()
        D/LeftFragement: onResume()
        W/EGL_emulation: eglSurfaceAttrib not implemented 3093 12436
        点击LeftFragment
        D/RightFragment: onAttach()
        D/RightFragment: onCreate()
        D/RightFragment: onCreateView()
        D/RightFragment: onActivityCreated()
        D/RightFragment: onStart()
        D/RightFragment: onResume()
        点击返回键(因为我们添加了返回栈)
        D/RightFragment: onPause()
        D/RightFragment: onStop()
        D/RightFragment: onDestroyView()
        D/RightFragment: onDestroy()
        D/RightFragment: onDetach()
        再次点击返回键
        D/LeftFragement: onPause()
        W/EGL_emulation: eglSurfaceAttrib not implemented 3093 12436
        D/LeftFragement: onStop()
        D/LeftFragement: onDestroyView()
        D/LeftFragement: onDestroy()
        D/LeftFragement: onDetach()
*/
