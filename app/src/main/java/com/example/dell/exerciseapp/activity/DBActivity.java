package com.example.dell.exerciseapp.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.dell.exerciseapp.R;
import com.example.dell.exerciseapp.db.DBConfig;
import com.example.dell.exerciseapp.db.DBManager;
import com.example.dell.exerciseapp.db.MyDBHelper;
import com.example.dell.exerciseapp.utils.BaseUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *  （SQLite）数据库的基本操作
 *      增删改查  ① 通过sql语句 进行编写
 *                ② 通过Android 提供的API 编写
 *
 *    实际开发中： 可能会用第三方框架数据库
 *                  参考有什么不同
 *
 */



public class DBActivity extends AppCompatActivity {

    @BindView(R.id.text_create_db)
    TextView textCreateDb;

    private MyDBHelper myDBHelper ;
    private SQLiteDatabase writableDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        ButterKnife.bind(this);
        /**创建数据库*/
        myDBHelper = DBManager.getMyDBHelper(this);

    }

    /**
     * getWritableDatabase()
     * getReadableDatabase()
     * 作用：创建或者打开数据库（如果当前数据库存在，则直接打开，不存在，则创建）
     * 重点： 是对SQL 语句的熟练，错一个字母都不行(以及标点符号)
     *        严格注意SQL 语句之间空格（每个字符必须用空格隔开）
     *
     */
    @OnClick({R.id.text_create_db,R.id.text_exeSql_add,R.id.text_exeSql_update,
            R.id.text_exeSql_delete,R.id.text_SqlAPI_add,R.id.text_SqlAPI_update,
            R.id.text_SqlAPI_delete,R.id.text_Sql_raw,R.id.text_tranlation})
    public void iniClick(View view) {
        switch (view.getId()){
            case R.id.text_create_db:
                writableDatabase = myDBHelper.getWritableDatabase();
                writableDatabase.close();
                BaseUtils.toast(this,"创建数据库成功");
                break;
            case R.id.text_exeSql_add:
                writableDatabase = myDBHelper.getWritableDatabase();
                /**删除之间所有的数据（但实际开发是不会这么做的，此处制作演示）*/
                String deleteAllSql = "delete  from " + DBConfig.TABLE_NAME;
                DBManager.exeSQL(writableDatabase, deleteAllSql);

                 String addSql = "insert into "+ DBConfig.TABLE_NAME+" values(0,'x',23)";
                 DBManager.exeSQL(writableDatabase,addSql);
                  String addSql2 = "insert into "+ DBConfig.TABLE_NAME+" values(1,'c',23)";
                 DBManager.exeSQL(writableDatabase,addSql2);
                 writableDatabase.close();
                BaseUtils.toast(this,"添加数据成功");

                break;
                /**事务的好处：保证数据的一致性，高效，相当于缓存*/
            case R.id.text_tranlation:
                writableDatabase = myDBHelper.getWritableDatabase();
                /**开启事务*/
                writableDatabase.beginTransaction();
                for (int i = 0; i < 100; i++) {
                    String transactionSql = "insert into "+ DBConfig.TABLE_NAME+" values("+i+",'小雪"+i+"',"+i+")";
                    writableDatabase.execSQL(transactionSql);
                }
                /**设置事务*/
                writableDatabase.setTransactionSuccessful();
                /**结束事务*/
                writableDatabase.endTransaction();
                writableDatabase.close();
                BaseUtils.toast(this,"插入成功,（插入先做清除数据工作)");
                break;

            case R.id.text_exeSql_update:
                writableDatabase = myDBHelper.getWritableDatabase();
                String updateSql = "update "+DBConfig.TABLE_NAME+" set "+DBConfig.KEY_NAME+" = 'xiao' where "+DBConfig.KEY_ID+"= 1";
                DBManager.exeSQL(writableDatabase,updateSql);
                writableDatabase.close();
                BaseUtils.toast(this,"修改数据成功");
                break;
            case R.id.text_exeSql_delete:
                writableDatabase = myDBHelper.getWritableDatabase();
                String deleteSql = "delete from "+DBConfig.TABLE_NAME+" where "+DBConfig.KEY_ID+"=1";
                DBManager.exeSQL(writableDatabase,deleteSql);
                writableDatabase.close();
                BaseUtils.toast(this,"删除数据成功");
                break;
                /**底层执行的还是SQL语句*/
            case R.id.text_SqlAPI_add:
                writableDatabase = myDBHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(DBConfig.KEY_ID,3);
                contentValues.put(DBConfig.KEY_NAME,"fuck");
                contentValues.put(DBConfig.KEY_AGE,14);
                long insert = writableDatabase.insert(DBConfig.TABLE_NAME, null, contentValues);
                if (insert >0){
                    BaseUtils.toast(this,"插入成功");
                }else {
                    BaseUtils.toast(this,"插入失败");
                }
                writableDatabase.close();
                break;
            case R.id.text_SqlAPI_update:
                writableDatabase = myDBHelper.getWritableDatabase();
                ContentValues contentValues1 = new ContentValues();
                contentValues1.put(DBConfig.KEY_NAME,"aaa");
                /**占位符，当需求并不知道要修改哪一行时*/
               // writableDatabase.update(DBConfig.TABLE_NAME,contentValues1,DBConfig.KEY_ID+"=3",null);
               int update = writableDatabase.update(DBConfig.TABLE_NAME,contentValues1,DBConfig.KEY_ID+"=?",new String[]{"3"});
                if (update >0){
                    BaseUtils.toast(this,"修改成功");
                }else {
                    BaseUtils.toast(this,"修改失败");
                }
                writableDatabase.close();
                break;
            case R.id.text_SqlAPI_delete:
                writableDatabase = myDBHelper.getWritableDatabase();
                int delete = writableDatabase.delete(DBConfig.TABLE_NAME,DBConfig.KEY_ID+"=?",new String[]{"2"});
                if (delete >0){
                    BaseUtils.toast(this,"删除成功");
                }else {
                    BaseUtils.toast(this,"删除失败");
                }
                writableDatabase.close();
                break;
            case R.id.text_Sql_raw:
                startActivity(new Intent(this,DBTwoActivity.class));
                BaseUtils.toast(this,"需要把数据库girl.db 复制到SD卡根目录下对应的位置");
            break;
                default:
        }

    }
}
