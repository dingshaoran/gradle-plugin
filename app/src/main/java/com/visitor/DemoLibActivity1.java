package com.visitor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.gradle.plugin.demo.R;

/**
 * Created by jzj on 2018/3/29.
 */
public class DemoLibActivity1 extends Activity {
    private static final String TAG = "DemoLibActivity1";

    private static LocationListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);//锁屏显示
        setContentView(R.layout.activity_text);
        TextView textView = findViewById(R.id.text);
//        textView.setText(" " + DemoRouter.getService(LibraryModule.class, DemoConstant.LIBMODULE2, 1).getModuleName());
//        DexClassLoader dexClassLoader = new DexClassLoader("", "", "", getClassLoader());
//        Log.i("aabbcdd", dexClassLoader + "");
    }

    @SuppressLint("MissingPermission")
    @DemoAnno()
    void dsr1(String anno) {
        Log.i(TAG, "dsr1");
    }

    public class Inner2 {
        @SuppressLint("MissingPermission")
        @DemoAnno()
        void dsr2(String anno) {
            Log.i(TAG, "dsr2");
        }
    }

    public static class Inner3 {

        @SuppressLint("MissingPermission")
        @DemoAnno()
        void dsr3(String anno) {
            Log.i(TAG, "dsr3");
        }
    }
}
