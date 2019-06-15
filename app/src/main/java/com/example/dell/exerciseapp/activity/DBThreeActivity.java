package com.example.dell.exerciseapp.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.dell.exerciseapp.R;
import com.example.dell.exerciseapp.adapter.MListViewAdapter;
import com.example.dell.exerciseapp.bean.PersonBean;
import com.example.dell.exerciseapp.db.DBConfig;
import com.example.dell.exerciseapp.db.DBManager;
import com.example.dell.exerciseapp.db.MyDBHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 数据库之分页查询
 */

public class DBThreeActivity extends AppCompatActivity {

    @BindView(R.id.lv_page_data)
    ListView lvPageData;

    private  boolean isDivPage;
    private MyDBHelper myDBHelper;
    /**总共多少条*/
    private  int  totalSize ;
    /**总共多少页*/
    private  int  pageCcountSize;
    /**每页显示10条*/
    private  int  pagePerSize = 20;
    /**当前第几页*/
    private  int  currentPage = 1;
    private   List<PersonBean>  list;

    private   SQLiteDatabase writableDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbthree);
        ButterKnife.bind(this);
        myDBHelper = DBManager.getMyDBHelper(this);
        initData();


    }

    /**
     * 插入100条数据到数据库
     */
    private void initData() {
       // String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "文件名";
        /**内部存储*/
        final String path =   Environment.getDataDirectory().getParentFile()+"/mnt/sdcard/other/girl";
        writableDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
       // SQLiteDatabase writableDatabase = myDBHelper.getWritableDatabase();
        /**此处清除表中的数据也可以，但上面开发数据的权限 是 只读  多以没法操作
         * */
       /* String deleteSql = "delete  from " + DBConfig.TABLE_NAME;
        DBManager.exeSQL(writableDatabase, deleteSql);
        for (int i = 0; i < 100; i++) {
            String insertSql = "insert into " + DBConfig.TABLE_NAME + " values(" + i + "," + "'小雪 "+ i + "'," + i + ")";
            DBManager.exeSQL(writableDatabase, insertSql);
        }*/
        totalSize = DBManager.getTotalDataSize(writableDatabase,DBConfig.TABLE_NAME);
        /**向上取整*/
        pageCcountSize = (int) Math.ceil(totalSize/(double) pagePerSize);
         list =  DBManager.getByCurrentPageOnResult(writableDatabase,DBConfig.TABLE_NAME,currentPage,pagePerSize);
        final MListViewAdapter mListViewAdapter = new MListViewAdapter(this,list);
        lvPageData.setAdapter(mListViewAdapter);

        lvPageData.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                /**还可以分页并且停止滚动*/
                 if (!isDivPage && AbsListView.OnScrollListener.SCROLL_STATE_IDLE == scrollState){
                     currentPage++;
                     writableDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
                     list.addAll(DBManager.getByCurrentPageOnResult(writableDatabase,DBConfig.TABLE_NAME,
                             currentPage,pagePerSize));
                     mListViewAdapter.notifyDataSetChanged();
                 }else {
                     writableDatabase.close();
                 }
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int i2) {
                isDivPage = (firstVisibleItem + visibleItemCount) == totalSize;
            }
        });


    }
}
