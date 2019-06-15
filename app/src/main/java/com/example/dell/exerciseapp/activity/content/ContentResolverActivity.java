package com.example.dell.exerciseapp.activity.content;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dell.exerciseapp.R;
import com.example.dell.exerciseapp.utils.BaseUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 内容解析器
 *    两种： ① 使用系统提供的内容提供器 来读取相应的程序中的数据
 *           ② 我们自定义内容提供器，让我们的数据可以被外部访问
 * <p>
 * 场景： 获取手机内联系人
 * ① 申请权限
 * ② 获取逻辑
 */

public class ContentResolverActivity extends AppCompatActivity {

    @BindView(R.id.lv_contacts)
    ListView lvContacts;

    private ArrayAdapter<String>  arrayAdapter;
    private List<String> contactsList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_resolver);
        ButterKnife.bind(this);
        initData();
        initApplyPermission();


    }

    private void initApplyPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},1);
        }else {
            readContacts();
        }
    }


    /**
     * 读取联系人
     *
     * 当然： 我们还可以读取数据库内容
     *        必须保证路径的正确
     *        Uri uri = Uri.parse("content:com.example.dell.exerciseapp.provider/表名");
     *        然后我们就可以把下面的uri 替换掉
     *        当然里面对应的逻辑也要变
     *        cursor.getString（“列名”）
     */
    private void readContacts() {
        Cursor cursor = null;
        try{
        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,null,null,null);
        if (cursor != null){
            while (cursor.moveToNext()){
                String displayName  = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String displayPhone  = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contactsList.add(displayName+"\n"+displayPhone);
            }
            arrayAdapter.notifyDataSetChanged();

        }
        }catch (Exception e){

        }finally {
            if (cursor != null){
                cursor.close();
            }
        }
    }

    private void initData() {
        contactsList =new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,contactsList);
        lvContacts.setAdapter(arrayAdapter);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case  1:
                if (grantResults.length>0 &&grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    readContacts();
                }else {
                    BaseUtils.toast(this,"请打开权限");
                }
                break;
            default:
        }
    }
}
