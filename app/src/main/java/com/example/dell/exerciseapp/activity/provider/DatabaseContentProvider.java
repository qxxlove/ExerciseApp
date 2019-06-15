package com.example.dell.exerciseapp.activity.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.dell.exerciseapp.db.DBConfig;
import com.example.dell.exerciseapp.db.DBManager;
import com.example.dell.exerciseapp.db.MyDBHelper;

/**
 * 实现场景：
 *     我们 访问 已存在的数据库中的 两种表的 数据 ，
 *     并提供相应的内容url,让外部（其他APP）可以访问这些数据
 *
 */


public class DatabaseContentProvider extends ContentProvider {

    public static final int  TABLE1_DIR = 0;
    public static final int  TABLE1_TIME = 1;
    public static final int  TABLE2_DIR = 2;
    public static final int  TABLE2_TIME = 3;

    private static UriMatcher uriMatcher;

    /**包名*/
    public static final String  AUTHORITY = "com.example.dell.exerciseapp.activity.provider";

    private MyDBHelper myDBHelper ;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,DBConfig.TABLE_NAME_BOOK,TABLE1_DIR);
        uriMatcher.addURI(AUTHORITY,DBConfig.TABLE_NAME_BOOK+"/#",TABLE1_TIME);
        uriMatcher.addURI(AUTHORITY,DBConfig.TABLE_NAME_CATEGORY,TABLE2_DIR);
        uriMatcher.addURI(AUTHORITY,DBConfig.TABLE_NAME_CATEGORY+"/#",TABLE2_TIME);

    }
    public DatabaseContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase writableDatabase = myDBHelper.getWritableDatabase();
        int deleteRows = 0;
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
                deleteRows = writableDatabase.delete(DBConfig.TABLE_NAME_BOOK,selection,
                        selectionArgs);
                break;
            case TABLE1_TIME:
                String bookId = uri.getPathSegments().get(1);
                deleteRows = writableDatabase.delete(DBConfig.TABLE_NAME_BOOK,"id = ?",
                        new String[]{bookId});
                break;
            case TABLE2_DIR:
                deleteRows = writableDatabase.delete(DBConfig.TABLE_NAME_CATEGORY,selection,
                        selectionArgs);
                break;
            case TABLE2_TIME:
                String cateId = uri.getPathSegments().get(1);
                deleteRows = writableDatabase.delete(DBConfig.TABLE_NAME_CATEGORY,"id = ?",
                        new String[]{cateId});
                break;


            default:
        }
        return  deleteRows;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.dell.exerciseapp.provider."+DBConfig.TABLE_NAME_BOOK;
            case TABLE1_TIME:
                return "vnd.android.cursor.item/vnd.com.example.dell.exerciseapp.provider."+DBConfig.TABLE_NAME_BOOK;
            case TABLE2_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.dell.exerciseapp.provider."+DBConfig.TABLE_NAME_CATEGORY;
            case TABLE2_TIME:
                return "vnd.android.cursor.item/vnd.com.example.dell.exerciseapp.provider."+DBConfig.TABLE_NAME_CATEGORY;

        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase writableDatabase = myDBHelper.getWritableDatabase();
       Uri uriReturn = null;
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
            case TABLE1_TIME:
                long insert = writableDatabase.insert(DBConfig.TABLE_NAME_BOOK, null, values);
                uriReturn = Uri.parse("content://" + AUTHORITY + DBConfig.TABLE_NAME_BOOK +insert);
                break;
            case TABLE2_DIR:
            case TABLE2_TIME:
                long insert2 = writableDatabase.insert(DBConfig.TABLE_NAME_CATEGORY, null, values);
                uriReturn = Uri.parse("content://" + AUTHORITY + DBConfig.TABLE_NAME_CATEGORY +insert2);

                break;


            default:
        }
        return  uriReturn;
    }

    @Override
    public boolean onCreate() {
        myDBHelper = DBManager.getMyDBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase readableDatabase = myDBHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
                cursor = readableDatabase.query(DBConfig.TABLE_NAME_BOOK,projection,selection,
                        selectionArgs,null,null,sortOrder);
                break;
            case TABLE1_TIME:
                /**getPathSegments 作用： 会将内容URI权限之后的部分以 “/”  符合进行分割，把分割好的存入一个集合，
                 * 集合的第0 个位置存放的路径，第1个位置存储的id */
                String bookId = uri.getPathSegments().get(1);
                cursor = readableDatabase.query(DBConfig.TABLE_NAME_BOOK,projection,"id = ?",
                        new String[]{bookId},null,null,sortOrder);
                break;
            case TABLE2_DIR:
                cursor = readableDatabase.query(DBConfig.TABLE_NAME_CATEGORY,projection,selection,
                        selectionArgs,null,null,sortOrder);
                break;
            case TABLE2_TIME:
                String cateId = uri.getPathSegments().get(1);
                cursor = readableDatabase.query(DBConfig.TABLE_NAME_CATEGORY,projection,"id = ?",
                        new String[]{cateId},null,null,sortOrder);
                break;


            default:
        }
        return  cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase writableDatabase = myDBHelper.getWritableDatabase();
        int updataRows = 0;
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
               updataRows = writableDatabase.update(DBConfig.TABLE_NAME_BOOK,values,selection,
                        selectionArgs);
                break;
            case TABLE1_TIME:
                String bookId = uri.getPathSegments().get(1);
                updataRows = writableDatabase.update(DBConfig.TABLE_NAME_BOOK,values,"id = ?",
                        new String[]{bookId});
                break;
            case TABLE2_DIR:
                updataRows = writableDatabase.update(DBConfig.TABLE_NAME_CATEGORY,values,selection,
                        selectionArgs);
                break;
            case TABLE2_TIME:
                String cateId = uri.getPathSegments().get(1);
                updataRows = writableDatabase.update(DBConfig.TABLE_NAME_CATEGORY,values,"id = ?",
                        new String[]{cateId});
                break;


            default:
        }
        return  updataRows;
    }
}
