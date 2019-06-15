package com.example.dell.exerciseapp.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.dell.exerciseapp.R;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 参考： https://www.jianshu.com/p/2de0113b3164
 *
 * 思考： 手机的内部存储和外部存储是如何区分的？
 *        手机上如何查看，手机上是否应该有默认的文件夹
 *
 * 结论：  我们手机的根目录 的根路径用 “/” 表示
 *                   下面有很多文件夹，有我们常见的： System
 *                                                    Cache
 *                                                    storage :  (也就是我们所说得外部存储)
 *                                                            emulated ： （外部存储下的私有目录）
 *                                                                     0： （此处不只有0 ，只是列举我们常见的）
 *                                                                     Android (也就是我们开发常用到的)
 *                                                            sdcard0 ：   （外部存储下的九个公有目录）
 *
 *
 *
 *
 */

public class PathActivity extends AppCompatActivity {

    @BindView(R.id.text_path_one)
    TextView textPathOne;
    @BindView(R.id.text_path_two)
    TextView textPathTwo;
    @BindView(R.id.text_path_three)
    TextView textPathThree;
    @BindView(R.id.text_path_four)
    TextView textPathFour;
    @BindView(R.id.text_path_five)
    TextView textPathFive;
    @BindView(R.id.text_path_six)
    TextView textPathSix;
    @BindView(R.id.text_path_seven)
    TextView textPathSeven;
    @BindView(R.id.text_path_eight)
    TextView textPathEight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {

        /**内部存储路径：
         *     一些私密数据: 用户信息
         * */
        File dataDirectory = Environment.getDataDirectory();
        textPathOne.setText("内部存储之Environment.getDataDirectory()：" + dataDirectory.toString());
        File downloadCacheDirectory = Environment.getDownloadCacheDirectory();
        textPathTwo.setText("内部存储之Environment.getDownloadCacheDirectory()：" + downloadCacheDirectory.toString());
        File rootDirectory = Environment.getRootDirectory();
        textPathThree.setText("内部存储之Environment.getRootDirectory()：" + rootDirectory.toString());
        File parentFile = Environment.getDataDirectory().getParentFile();
        textPathFour.setText("内部存储之Environment.getDataDirectory().getParentFile()：" + parentFile.toString());

        /**外部存储分为 SD卡 和 扩展内存卡（这个才是我们平时(市面上)说的SD卡（128M,1G,2G,4G，8G））*/
        /**SD卡 路径 ：Environment.getExternalStorageDirectory()
         *    其中android 又为SD 卡 提供了九大共有目录 ：
         *    例如：Environment.getExternalStoragePublicDirectory(DIRECTORY_RINGTONES)
         *          （）括号中的参数有九个,而对应的路径：/storage/sdcard0/Ringtones
         *
         *     对于开发者而言： 我们开发的APP 不重要的数据应该放在SD 下的私有目录：
         *          /storage/emulated/0/Android/data/(包名)/后面就是自己新建的文件夹及文件
         *
         *
         *     注意：   /storage/sdcard0/      SD卡下 共有目录
         *              /storage/emulated/0/   SD卡下 私有目录
         *
         *              Environment.getExternalStorageDirectory()
         *              Environment.getExternalStorageDirectory().getAbsolutePath()
         *              两者指的路径是同一个。
         *
         *    */
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        textPathFive.setText("外部存储内置存储卡路径：" + externalStorageDirectory.toString());

        File externalCacheDir = getApplicationContext().getExternalCacheDir();
        textPathSix.setText("外部存储内置存储卡cache文件夹下路径：" + externalCacheDir.toString());
       // File externalFileDir = getApplicationContext().getExternalFilesDir("存在的文件名");
        //textPathSeven.setText("外部存储内置存储卡file文件夹下路径：" + externalFileDir.toString());

        textPathEight.setText(getExtendedMemoryPath(this));
    }


    /**
     * 获取是否插入我们自己的外置SD卡
     * @param mContext
     * @return
     * 原理： 该方法主要是通过反射的方式使用在sdk中被 隐藏 的类 StroageVolume 中
     *        的方法getVolumeList()，
     *         获取外部存储的路径。
     */
    private static String getExtendedMemoryPath(Context mContext) {
        StorageManager mStorageManager = (StorageManager) mContext.getSystemService(Context.STORAGE_SERVICE);
        Class<?> storageVolumeClazz = null;
        try {
            storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
            Method getVolumeList = mStorageManager.getClass().getMethod("getVolumeList");
            Method getPath = storageVolumeClazz.getMethod("getPath");
            Method isRemovable = storageVolumeClazz.getMethod("isRemovable");
            Object result = getVolumeList.invoke(mStorageManager);
            final int length = Array.getLength(result);
            for (int i = 0; i < length; i++) {
                Object storageVolumeElement = Array.get(result, i);
                String path = (String) getPath.invoke(storageVolumeElement);
                boolean removable = (Boolean) isRemovable.invoke(storageVolumeElement);
                if (removable) {
                    return path;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return "没有挂载内存卡";
    }


}
