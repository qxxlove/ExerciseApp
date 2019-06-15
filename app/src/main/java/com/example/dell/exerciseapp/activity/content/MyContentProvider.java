package com.example.dell.exerciseapp.activity.content;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 *
 * 以下的 包名.provider  都是authorities 属性
 *         具体可在清单文件中查看
 *         因为我们新建一个内容提供器，
 *         必须制定其 authorities （这个可以是任意的，但是我们一般 包名.provider）
 *
 *
 *   一个标准的内容uri 地址写法：
 *           content://包名.provider/表名
 *           content://包名.provider/表名/1 (1表示id为1的数据)
 *
 *           通配符：
 *           一个能匹配任意表的内容URI
 *           content://包名.provider/*
 *           一个能匹配某一张表的任意一行的数据的内容URI
 *           content://包名.provider/表名/#
 *
 *
 */

public class MyContentProvider  extends ContentProvider {

    public static final int  TABLE1_DIR = 0;
    public static final int  TABLE1_TIME = 1;
    public static final int  TABLE2_DIR = 2;
    public static final int  TABLE2_TIME = 3;

    private static UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.dell.exerciseapp.provider","table1",TABLE1_DIR);
        uriMatcher.addURI("com.example.dell.exerciseapp.provider","table1/#",TABLE1_TIME);
        uriMatcher.addURI("com.example.dell.exerciseapp.provider","table2",TABLE2_DIR);
        uriMatcher.addURI("com.example.dell.exerciseapp.provider","table2/#",TABLE2_TIME);

    }


    /**
     * 初始化内容提供器调用
     * 一般数据库的创建和升级在这里完成
     * @return  true 表示成功 false 表示失败
     */
    @Override
    public boolean onCreate() {
        return false;
    }

    /**
     *  增删改查里面同样，先判断uri类型，在做相应的逻辑
     * @param uri  查询那张表
     * @param strings 查询那些列
     * @param s   约束条件
     * @param strings1  约束条件
     * @param s1   排序
     * @return  返回对象Cursor
     */
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        switch (uriMatcher.match(uri)){
            /**查询table1 表中的所有数据*/
            case TABLE1_DIR:
                break;
            /**查询table1 表中的单挑数据*/
            case TABLE1_TIME:
                break;
            /**查询table2 表中的所有数据*/
            case TABLE2_DIR:
                break;
            /**查询table2 表中的单条数据*/
            case TABLE2_TIME:
                break;
                default:
        }
        return null;
    }

    /**
     *  根据传来的uri 返回对应的MIME 类型
     *      ① 首先必须以vnd 开头
     *      ② 中间 如果内容uri以路径结尾 则 ： android.cursor.dir/
     *              如果内容uri以id结尾 则：  android.cursor.item/
     *      ③ 最后   vnd.包名.provider.表名（注意小黑点）
     *
     *      例如： vnd.android.cursor.dir/vnd.com.example.dell.exerciseapp.provider.table1
     *             vnd.android.cursor.item/vnd.com.example.dell.exerciseapp.provider.table1
     *
     * @param uri
     * @return
     */
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.dell.exerciseapp.provider.table1";
            case TABLE1_TIME:
                return "vnd.android.cursor.item/vnd.com.example.dell.exerciseapp.provider.table1";
            case TABLE2_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.dell.exerciseapp.provider.table2";
            case TABLE2_TIME:
                return "vnd.android.cursor.item/vnd.com.example.dell.exerciseapp.provider.table2";

        }
        return null;
    }


    /**
     *
     * @param uri 确定要添加到那张表
     * @param contentValues  待添加的参数
     * @return 返回一个用于表示真条新纪录的uri
     */
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    /**
     *
     * @param uri  要删除那种表的数据
     * @param s    约束条件
     * @param strings 约束条件
     * @return  返回操作的行数
     */
    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    /**
     *
     * @param uri  更新那一张表
     * @param contentValues  新数据（要更新的）
     * @param s      约束条件
     * @param strings  约束条件
     * @return   更新的哪一行
     */
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
