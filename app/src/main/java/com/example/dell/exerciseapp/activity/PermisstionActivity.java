package com.example.dell.exerciseapp.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.dell.exerciseapp.R;
import com.example.dell.exerciseapp.utils.BaseUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 权限申请
 *
 * 针对特殊机型 需要特殊处理
 *
 */



public class PermisstionActivity extends AppCompatActivity {

    @BindView(R.id.text_call_phone)
    TextView textCallPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permisstion);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.text_call_phone})
    public  void  initClick(View view){
       switch (view.getId()){
           case R.id.text_call_phone:
               if (ContextCompat.checkSelfPermission(this,
                       Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                   ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},1);
               }else {
                   call();
               }
               break;

            default:
       }

    }

    private void call() {
        try{
            Intent intent =new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:10086"));
            startActivity(intent);
        }catch (SecurityException e){
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case  1:
                if (grantResults.length>0 &&grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    call();
                }else {
                    BaseUtils.toast(this,"请打开权限");
                }
                break;
                default:
        }
    }
}
