package com.example.dell.exerciseapp.activity.provider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.dell.exerciseapp.R;
import com.example.dell.exerciseapp.db.DBConfig;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 这个类应该放在另一个APP，进行跨进程数据访问测试。
 */

public class ProviderContentActivity extends AppCompatActivity {

    /**包名*/
    public static final String  AUTHORITY = "com.example.dell.exerciseapp.activity.provider/";
    public static final String  TABLE_BOOK = "book";
    public static final String  TABLE_CATEGORY = "category";

   private  String  newId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_content);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.add_to_book,R.id.query_to_book,R.id.update_to_book,
            R.id.delete_to_book})
    public  void  initClick  (View view){
        switch (view.getId()) {
            case R.id.add_to_book:
                Uri uri = Uri.parse("content://"+AUTHORITY+TABLE_BOOK);
                ContentValues contentValues = new ContentValues();
                contentValues.put(DBConfig.KEY_BOOK_ID,"1");
                contentValues.put(DBConfig.KEY_BOOK_NAME,"金瓶梅");
                contentValues.put(DBConfig.KEY_BOOK_AUTHOR,"你猜");
                contentValues.put(DBConfig.KEY_BOOK_PAGE,"1000000");
                contentValues.put(DBConfig.KEY_BOOK_PRICE,"22.85");
                Uri newUri = getContentResolver().insert(uri,contentValues);
                newId = newUri.getPathSegments().get(1);
                break;
            case R.id.query_to_book:
                 uri = Uri.parse("content://"+AUTHORITY+TABLE_BOOK);
                Cursor cursor = getContentResolver().query(uri,null,null,null,null);
                if (cursor != null){
                    while (cursor.moveToNext()){
                        String name = cursor.getString(cursor.getColumnIndex(DBConfig.KEY_BOOK_NAME));
                        String author = cursor.getString(cursor.getColumnIndex(DBConfig.KEY_BOOK_AUTHOR));
                        String page = cursor.getString(cursor.getColumnIndex(DBConfig.KEY_BOOK_PAGE));
                        String price = cursor.getString(cursor.getColumnIndex(DBConfig.KEY_BOOK_PRICE));
                        Log.d(getClass().getSimpleName(),"查询结果"+name+"/n"+author+"/n"+page+"/n"+price);
                    }
                }
                break;
            case R.id.update_to_book:
                 uri = Uri.parse("content://"+AUTHORITY+TABLE_BOOK+"/"+newId);
                 contentValues = new ContentValues();
                contentValues.put(DBConfig.KEY_BOOK_ID,"1");
                contentValues.put(DBConfig.KEY_BOOK_NAME,"金瓶梅2");
                contentValues.put(DBConfig.KEY_BOOK_AUTHOR,"不告诉你");
                contentValues.put(DBConfig.KEY_BOOK_PAGE,"1000000");
                contentValues.put(DBConfig.KEY_BOOK_PRICE,"22.85");
                getContentResolver().update(uri,contentValues,null,null);
                break;
            case R.id.delete_to_book:
                uri = Uri.parse("content://"+AUTHORITY+TABLE_BOOK+"/"+newId);
                getContentResolver().delete(uri,null,null);

                break;

            default:
        }
    }

}
