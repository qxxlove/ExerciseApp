package com.example.dell.exerciseapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * SQLite帮助类
 */

public class MyDBHelper extends SQLiteOpenHelper {

    public static final String TAG ="MyDBHelper";

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * 将数据库的版本修改为比原来版本大，就会触发onUpgrade();
     *  场景： 此处我们需要在数据库添加一张表 girl
     *         原来的版本号 1 现在修改为 2
     *         在onUpgrade() 执行添加的逻辑
     * @param context
     */
    public  MyDBHelper (Context context){
        super(context,DBConfig.DB_NAME,null,DBConfig.DB_VERSION);
    }

    /**
     * onCreate 只会执行一次，那就是数据库创建的时候
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG,"onCreate--------");
        String sql = "create table "+DBConfig.TABLE_NAME+"("+DBConfig.KEY_ID+" Integer primary key, "
                +DBConfig.KEY_NAME+" Varchar(100),"+DBConfig.KEY_AGE+" Integer)";
        sqLiteDatabase.execSQL(sql);
        String sql1 = "create table "+DBConfig.TABLE_NAME_BOOK+"("+DBConfig.KEY_BOOK_ID+" Integer primary key, "
                +DBConfig.KEY_BOOK_NAME+" Varchar(100),"+DBConfig.KEY_BOOK_AUTHOR+" Varchar(100),"
                +DBConfig.KEY_BOOK_PAGE+" Varchar(100),"+DBConfig.KEY_BOOK_PRICE+" Varchar(100))";
        sqLiteDatabase.execSQL(sql1);
    }

    /**
     * 数据库升级
     * @param sqLiteDatabase
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d(TAG,"onUpgrade--------");
        sqLiteDatabase.execSQL("drop table if exists "+DBConfig.TABLE_NAME_G);
        String sql = "create table "+DBConfig.TABLE_NAME_G+"("+DBConfig.KEY_ID_G+" Integer primary key, "
                +DBConfig.KEY_NAME_G+" Varchar(100),"+DBConfig.KEY_AGE_G+" Integer)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Log.d(TAG,"onOpen---------");

    }
}
