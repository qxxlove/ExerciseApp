package com.example.dell.exerciseapp.fargment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.exerciseapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * LeftFragment
 */

public class LeftFragement extends Fragment {

    public static final String TAG = "LeftFragement";
    @BindView(R.id.text_left_click)
    TextView textLeftClick;
    Unbinder unbinder;
    @BindView(R.id.text_time_dealWith_before)
    TextView textTimeDealWithBefore;
    @BindView(R.id.text_time_dealWith_after)
    TextView textTimeDealWithAfter;

    private String TIME = "2017-10-16 07:28:50";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach()");
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        Log.d(TAG, "onAttachFragment()");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.left_fragment_alyout, container, false);
        unbinder = ButterKnife.bind(this, view);
        Log.d(TAG, "onCreateView()");

        textTimeDealWithBefore.setText(TIME);
        String TIME_DEAL =  TIME.replace("\n","").replaceAll("\\s*","");
        textTimeDealWithAfter.setText(TIME_DEAL);

        return view;
    }

    @OnClick({R.id.text_left_click})
    public void initclick(View view) {
        switch (view.getId()) {
            case R.id.text_left_click:

                break;
            default:
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated()");

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");

    }


    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");

    }


    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView()");
        unbinder.unbind();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");

    }


    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach()");

    }
}
