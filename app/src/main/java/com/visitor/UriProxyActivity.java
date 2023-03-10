package com.visitor;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.design.internal.ThemeEnforcement;
import android.util.Log;

import androidx.annotation.Nullable;

import com.gradle.plugin.demo.R;

/**
 * 接收所有外部跳转的ProxyActivity
 * <p>
 * Created by jzj on 2018/4/9.
 */

public class UriProxyActivity extends Activity {


    private static final String TAG = "UriProxyActivitya";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        DemoRouter.startUri(new DemoUriRequest(this, DemoConstant.TEST_LIB1));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.USE_FULL_SCREEN_INTENT, Manifest.permission.FOREGROUND_SERVICE}, 1);
        }
        ReplaceClickDemo repalceClickDemo = new ReplaceClickDemo();
        findViewById(R.id.tv3).setOnClickListener(v -> {
            repalceClickDemo.click(UriProxyActivity.this);
        });

        ThemeEnforcement te = new ThemeEnforcement();//ThemeEnforcement1 子类也替换了  ThemeEnforcement 没替换会崩溃
        TypedArray typedArray = ThemeEnforcement.obtainStyledAttributesStatic(this, null, R.styleable.ActionBar, R.styleable.ActionBar_indeterminateProgressStyle, R.style.Base_Widget_AppCompat_ActionBar);
        TypedArray typedArray2 = te.obtainStyledAttributes(this, null, R.styleable.ActionBar, R.styleable.ActionBar_indeterminateProgressStyle, R.style.Base_Widget_AppCompat_ActionBar);
        boolean typedBoolean = ThemeEnforcement.obtainBooleanStatic(this, null, R.styleable.ActionBar, R.styleable.ActionBar_indeterminateProgressStyle, R.style.Base_Widget_AppCompat_ActionBar);
        boolean typedBoolean2 = te.obtainBoolean(this, null, R.styleable.ActionBar, R.styleable.ActionBar_indeterminateProgressStyle, R.style.Base_Widget_AppCompat_ActionBar);
        long typedLong = ThemeEnforcement.obtainLongStatic(this, null, R.styleable.ActionBar, R.styleable.ActionBar_indeterminateProgressStyle, R.style.Base_Widget_AppCompat_ActionBar);
        long typedLong2 = te.obtainLong(this, null, R.styleable.ActionBar, R.styleable.ActionBar_indeterminateProgressStyle, R.style.Base_Widget_AppCompat_ActionBar);
        ThemeEnforcement.obtainVoidStatic(this, null, R.styleable.ActionBar, R.styleable.ActionBar_indeterminateProgressStyle, R.style.Base_Widget_AppCompat_ActionBar);
        te.obtainVoid(this, null, R.styleable.ActionBar, R.styleable.ActionBar_indeterminateProgressStyle, R.style.Base_Widget_AppCompat_ActionBar);
        Log.i(TAG, " " + typedBoolean + " " + typedBoolean2 + " " + typedLong + " " + typedLong2);
        dsr1("aaa");
        new Inner2().dsr2("aaac");
        new Inner3().dsr3("abb");
        new LibraryModule1().getModuleName();
        new LibraryModule2().getModuleName();
    }

    public String demoMethod() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("aaa");
        stringBuilder.append("bbbb");
        stringBuilder.append("ccc");
        stringBuilder.append("dddd");
        stringBuilder.toString();
        System.out.println("aaa");
        int i = 2 / 1;
        return "aaa";
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
