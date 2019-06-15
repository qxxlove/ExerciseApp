package com.example.dell.exerciseapp.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.example.dell.exerciseapp.bean.PersonBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2018/7/16.
 */

public class DBManager {

    public  static  MyDBHelper myDBHelper ;
    public  static  MyDBHelper getMyDBHelper(Context context){
        if (myDBHelper == null){
            myDBHelper = new MyDBHelper(context);
        }
        return  myDBHelper;
    }


    /**
     * 执行SQL语句
     * @param sqLiteDatabase
     * @param sql
     */
    public  static  void  exeSQL (SQLiteDatabase sqLiteDatabase,String sql){
        if (sqLiteDatabase != null){
            if (!TextUtils.isEmpty(sql)){
                sqLiteDatabase.execSQL(sql);
            }
        }else {
            Log.e("DBManager","执行SQL语句异常了");
        }
    }


    /**
     * 根据查询语句得到查询结果
     * @param sqLiteDatabase
     * @param sql
     * @return
     */
    public  static  Cursor  rawBySqlResult (SQLiteDatabase sqLiteDatabase ,String sql){
        if (sqLiteDatabase != null){
            Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
            return cursor ;
        }
        return  null;
    }

    /**
     * 将Cursor转换为List
     * @param cursor
     * @return
     */
    public  static List<PersonBean>  cusorByList (Cursor cursor){
        List<PersonBean>  list = new ArrayList<>();
        if (cursor != null){
            while (cursor.moveToNext()){
                int columnIndex = cursor.getColumnIndex(DBConfig.KEY_ID);
                int id = cursor.getInt(columnIndex);
                int columnIndexName = cursor.getColumnIndex(DBConfig.KEY_NAME);
                String name = cursor.getString(columnIndexName);
                int columnIndexAge = cursor.getColumnIndex(DBConfig.KEY_AGE);
                int age = cursor.getInt(columnIndexAge);
                list.add(new PersonBean(id,name,age));
            }
        }

     return  list;
    }


    /**
     * 获取总共条目数
     * @param writableDatabase
     * @param tableName
     * @return
     */
    public static int getTotalDataSize(SQLiteDatabase writableDatabase, String tableName) {
        int count = 0;
        if (writableDatabase != null){
             String sql = "select * from "+tableName;
             Cursor cursor =  writableDatabase.rawQuery(sql,null);
              count =cursor.getColumnCount();
        }
        return count;
    }

    /**
     * 根据当前页数取出对应的条数
     * @param writableDatabase
     * @param currentPage
     * @param pagePerSize
     * @return
     */
    public static List<PersonBean> getByCurrentPageOnResult(SQLiteDatabase writableDatabase,String tableName, int currentPage, int pagePerSize) {
        int limitStart = (currentPage-1)*pagePerSize;
        if (writableDatabase != null){
            /**切记： limit ?,? ：第一个？每一页的第一个数的下标，？ 代表多少个数 代表*/
            String sql = "select * from "+tableName+" limit ?,?";
            Cursor cursor = writableDatabase.rawQuery(sql, new String[]{limitStart + "", pagePerSize + ""});
            return  cusorByList(cursor);
        }
        return null;
    }
}
