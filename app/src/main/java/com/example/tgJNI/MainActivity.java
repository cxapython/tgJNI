package com.example.tgJNI;

import static com.example.tgJNI.DecryptUtiles.copyFile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import com.example.tgJNI.databinding.ActivityMainBinding;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private static final String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final String DB_FILE_NAME = "cache4.db";
    private static int REQUEST_PERMISSION_CODE = 1;
    private static Context sApp = null;
    private MyDatabaseHelper dbHelper;
    public static final String DB_DIR_PATH = "/data/data/org.telegram.messenger.web/files";


    private String mCurrApkPath = Environment.getExternalStorageDirectory().getPath() + "/";
    private static final String COPY_WX_DATA_DB = "mycache4.db";
    String copyFilePath = mCurrApkPath + COPY_WX_DATA_DB;

    static {
        System.loadLibrary("mytgmessage");
    }

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE);
            }
        }
        //这部分是copy，后面测试完再放开
//        String path = DB_DIR_PATH + "/" + DB_FILE_NAME;
//        DecryptUtiles.execRootCmd("chmod 777 -R /data/data/org.telegram.messenger.web");
//        copyFile(path,copyFilePath);
//
//        DecryptUtiles.execRootCmd("chmod 777 -R "+copyFilePath);

        Intent intentOne = new Intent(this, MyService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intentOne);
        } else {
            startService(intentOne);
        }

//        dbHelper = new MyDatabaseHelper(MainActivity.this, 5);
//        dbHelper.getWritableDatabase();


    }
}