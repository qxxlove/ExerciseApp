package com.example.dell.exerciseapp.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.dell.exerciseapp.R;
import com.example.dell.exerciseapp.bean.PersonBean;
import com.example.dell.exerciseapp.db.DBConfig;
import com.example.dell.exerciseapp.db.DBManager;
import com.example.dell.exerciseapp.db.MyDBHelper;
import com.example.dell.exerciseapp.utils.BaseUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 数据库查询操作
 */

public class DBTwoActivity extends AppCompatActivity {

    @BindView(R.id.listView_dbTwoActivity)
    ListView listViewDbTwoActivity;
    private MyDBHelper myDBHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbtwo);
        ButterKnife.bind(this);
        myDBHelper = DBManager.getMyDBHelper(this);
        initGetData();
    }

    /**
     * 获取数据源
     * 需要把数据库job.db 复制到SD卡根目录下
     * 数据库复制到哪就取哪的地址
     *
     *
     */
    private void initGetData() {
        // String path, SQLiteDatabase.CursorFactory factory, int flags, DatabaseErrorHandler errorHandler
         /**外部存储*/
       // String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "文件名";
        /**内部存储*/
        String path =   Environment.getDataDirectory().getParentFile()+"/mnt/sdcard/other/girl";
        SQLiteDatabase sqLiteDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
         /**此处rawQuery 有问题*/
        // Cursor cursor = sqLiteDatabase.rawQuery("select * from " + DBConfig.TABLE_NAME +" where "+DBConfig.KEY_ID , new String[]{"id as _id"});
         /**只好使用query 语句，但是需注意 id 必须为"_id"
          *
          * 参考： https://blog.csdn.net/xiazdong/article/details/7705908
          *
          * */
        Cursor cursor = sqLiteDatabase.query(DBConfig.TABLE_NAME, new String[]{"id as _id","name","age"}, null, null, null, null, null,null);

        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this,
                R.layout.item_listview_db_layout,
                cursor, new String[]{"_id", DBConfig.KEY_NAME, DBConfig.KEY_AGE},
                new int[]{R.id.text_id, R.id.text_name, R.id.text_age}
                , SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        //listViewDbTwoActivity.setAdapter(simpleCursorAdapter);

        /**自定义Adapter*/
      //  Cursor cursorOne = sqLiteDatabase.rawQuery("select * from " + DBConfig.TABLE_NAME, null);
        Cursor cursorOne = sqLiteDatabase.query(DBConfig.TABLE_NAME, new String[]{"id as _id","name","age"}, null, null, null, null, null,null);
        MyCursorAdapter myCursorAdapter = new MyCursorAdapter(this,cursorOne);
        listViewDbTwoActivity.setAdapter(myCursorAdapter);

    }


    @OnClick({R.id.text_Sql_insert, R.id.text_Sql_raw, R.id.text_API_raw,R.id.text_listView_page
    })
    public void iniClick(View view) {
        switch (view.getId()) {
            /**插入30条语句，插入之前先将表中的数据删除（但实际开发是不会这么做的，此处制作演示,防止主键冲突）*/
            case R.id.text_Sql_insert:
                SQLiteDatabase writableDatabase = myDBHelper.getWritableDatabase();
                String deleteSql = "delete  from " + DBConfig.TABLE_NAME;
                DBManager.exeSQL(writableDatabase, deleteSql);
                for (int i = 0; i < 30; i++) {
                    String insertSql = "insert into " + DBConfig.TABLE_NAME + " values(" + i + ", '小雪 "+ i + "'," + i + ")";
                    DBManager.exeSQL(writableDatabase, insertSql);
                }
                writableDatabase.close();
                BaseUtils.toast(this,"插入30条数据成功");

                break;
            case R.id.text_Sql_raw:
                SQLiteDatabase writableDatabaseOne = myDBHelper.getWritableDatabase();
                String rawSQL = "select * from " + DBConfig.TABLE_NAME;
                Cursor cursor = DBManager.rawBySqlResult(writableDatabaseOne, rawSQL);
                List<PersonBean> list = DBManager.cusorByList(cursor);
                for (int i = 0; i < list.size(); i++) {
                    Log.d("Person:", list.get(i).toString());
                }
                writableDatabaseOne.close();
                BaseUtils.toast(this,"查询成功，请看Log日志");

                break;
            case R.id.text_API_raw:
                SQLiteDatabase writableDatabaseTwo = myDBHelper.getWritableDatabase();
                //String table,   表名
                // String[] columns, 字段名
                // String selection, 查询语句
                // String[] selectionArgs, 查询条件占位符
                // String groupBy,    null
                // String having,     null
                // String orderBy     排序
                Cursor query = writableDatabaseTwo.query(DBConfig.TABLE_NAME,
                        null,
                        DBConfig.KEY_ID + ">?",
                        new String[]{"10"},
                        null,
                        null,
                        DBConfig.KEY_ID + " desc");
                List<PersonBean> listAPI = DBManager.cusorByList(query);
                for (int i = 0; i < listAPI.size(); i++) {
                    Log.d("PersonTwo:", listAPI.get(i).toString());
                }
                writableDatabaseTwo.close();
                BaseUtils.toast(this,"查询成功，请看Log日志");

                break;

            case R.id.text_listView_page:
                startActivity(new Intent(this,DBThreeActivity.class));
                BaseUtils.toast(this,"需要把数据库girl.db 复制到SD卡根目录下对应的位置");

                break;
            default:
        }
    }


    public  class  MyCursorAdapter  extends CursorAdapter{

        public MyCursorAdapter(Context context, Cursor c) {
            super(context, c);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

            return LayoutInflater.from(context).inflate(R.layout.item_listview_db_layout,null);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView text_id = view.findViewById(R.id.text_id);
            TextView text_name = view.findViewById(R.id.text_name);
            TextView text_age = view.findViewById(R.id.text_age);

            text_id.setText(cursor.getInt(cursor.getColumnIndex("_id"))+"");
            text_name.setText(cursor.getString(cursor.getColumnIndex(DBConfig.KEY_NAME)));
            text_age.setText(cursor.getInt(cursor.getColumnIndex(DBConfig.KEY_AGE))+"");
        }
    }
}


  //                android.database.sqlite.SQLiteException: no such column: iddesc (code 1): , while compiling: SELECT * FROM job WHERE id>? ORDER BY iddesc
  //                解决： SQL语句有问题。  iddesc 之间少了一个空格