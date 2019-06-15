package com.example.dell.exerciseapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dell.exerciseapp.activity.BroadCastActivity;
import com.example.dell.exerciseapp.activity.DBActivity;
import com.example.dell.exerciseapp.activity.FragmentActivity;
import com.example.dell.exerciseapp.activity.PathActivity;
import com.example.dell.exerciseapp.activity.RecyclerViewStudyActivity;
import com.example.dell.exerciseapp.activity.SHaredPreferenceActivity;
import com.example.dell.exerciseapp.activity.content.ContentResolverActivity;
import com.example.dell.exerciseapp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.text_recyclerView_one)
    TextView textRecyclerViewOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.text_recyclerView_one,R.id.text_fragment_one,R.id.text_braocast_one,
            R.id.text_sharedPreference_one,R.id.text_DBActivity_one,R.id.text_file_path_one,
            R.id.text_content_resolver})
    public  void  initClick  (View view){
        switch (view.getId()) {
            case R.id.text_recyclerView_one:
                startActivity(new Intent(MainActivity.this, RecyclerViewStudyActivity.class));
                break;
            case R.id.text_fragment_one:
                startActivity(new Intent(MainActivity.this, FragmentActivity.class));
                break;
            case R.id.text_braocast_one:
                startActivity(new Intent(MainActivity.this, BroadCastActivity.class));
                break;
            case R.id.text_sharedPreference_one:
                startActivity(new Intent(MainActivity.this, SHaredPreferenceActivity.class));
                break;
            case R.id.text_DBActivity_one:
                startActivity(new Intent(MainActivity.this, DBActivity.class));
                break;
            case R.id.text_file_path_one:
                startActivity(new Intent(MainActivity.this, PathActivity.class));
                break;
            case R.id.text_content_resolver:
                startActivity(new Intent(MainActivity.this, ContentResolverActivity.class));
                break;
                default:
        }
    }

}
