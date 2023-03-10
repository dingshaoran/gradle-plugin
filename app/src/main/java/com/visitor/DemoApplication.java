package com.visitor;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;


/**
 * Created by jzj on 2018/3/19.
 */
public class DemoApplication extends Application {

    public static DemoApplication provideApplication() {
        return sApplication;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @SuppressLint("StaticFieldLeak")
    private static DemoApplication sApplication;

    @Override
    public void onCreate() {
        sApplication = this;
        super.onCreate();
    }

    public static Context getContext() {
        return sApplication;
    }
}
