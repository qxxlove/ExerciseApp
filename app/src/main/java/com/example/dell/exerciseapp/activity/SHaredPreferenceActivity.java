package com.example.dell.exerciseapp.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dell.exerciseapp.R;
import com.example.dell.exerciseapp.utils.BaseUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Android 中 数据存储方式
 * 1. 文件存储  （文件存储的路径默认在 data/data/<your package>/files 目录下）
 * 2. SharedPreference
 * 3. 数据库 （SQLite）
 */


public class SHaredPreferenceActivity extends AppCompatActivity {

    @BindView(R.id.edit_one)
    EditText editOne;
    @BindView(R.id.text_result_one)
    TextView textResultOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);
        ButterKnife.bind(this);
        initSP();
        initData();
    }


    private void initSP() {
        SharedPreferences.Editor sharedPreferences =
                getSharedPreferences("data", MODE_PRIVATE).edit();
        sharedPreferences.putInt("age", 15);
        sharedPreferences.putBoolean("isTrue", true);
        sharedPreferences.putString("love", "love");
        sharedPreferences.apply();

    }

    private void initData() {
        String loadResult = load();
        if (!TextUtils.isEmpty(loadResult)) {
            editOne.setText(loadResult);
            editOne.setSelection(loadResult.length());
            BaseUtils.toast(this, "文件中的数据加载成功");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        String inputText = editOne.getText().toString();
        if (!TextUtils.isEmpty(inputText)) {
            saveData(inputText);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @OnClick({R.id.text_get_sp_one})
    public void initClick(View view) {
        switch (view.getId()) {
            case R.id.text_get_sp_one:
                SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
                int age = sharedPreferences.getInt("age", 0);
                String name = sharedPreferences.getString("love", "");
                boolean isTrue = sharedPreferences.getBoolean("isTrue", false);
                textResultOne.setText("获取SP保存的数据成功:\n"+age+"\n"+name+"\n"+isTrue);
                break;

            default:
        }
    }


    /**
     * 文件流保存数据
     *
     * @param inputText Context.MODE_PRIVATE  直接覆盖同名文件
     *                  Context.MODE_APPEND   同名则在里面继续添加
     */
    private void saveData(String inputText) {
        FileOutputStream fileOutputStream = null;
        BufferedWriter bufferedWriter = null;

        try {
            fileOutputStream = openFileOutput("data", Context.MODE_PRIVATE);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            try {
                bufferedWriter.write(inputText);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 从文件中加载数据
     *
     * @return
     */
    private String load() {
        FileInputStream fileInputStream = null;
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            fileInputStream = openFileInput("data");
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line = "";
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();

    }

}
