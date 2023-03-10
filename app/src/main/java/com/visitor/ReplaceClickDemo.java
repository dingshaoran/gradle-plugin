package com.visitor;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class ReplaceClickDemo {
    private static final String TAG = "RepalceClickDemo";
    private static final String DEMO_ACTION = "demo_action";
    private int times = 0;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    };

    @SuppressLint({"MissingPermission", "NewApi"})
    public void click(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        times++;
        switch (times) {
            case 1:
                Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888).copyPixelsToBuffer(null);
                break;
            case 2:
                OkHttpClient build = new OkHttpClient.Builder().readTimeout(1000, TimeUnit.MINUTES).build();
                Log.i(TAG, "OkHttpClient " + build);
                break;
            case 3:
                Log.i(TAG, "click");
                break;
            case 4:
                Log.d(TAG, "click");
                break;
            case 5:
                Log.w(TAG, "click");
                break;
            case 6:
                Log.v(TAG, "click");
                break;
            case 7:
                Log.e(TAG, "click");
                break;
            case 8:
                Log.println(Log.DEBUG, TAG, "click");
                break;
            case 9:
                lm.requestSingleUpdate(LocationManager.GPS_PROVIDER, location -> {
                }, Looper.getMainLooper());
                break;
            case 10:
                lm.requestSingleUpdate(new Criteria(), location -> {
                }, Looper.getMainLooper());
                break;
            case 11:
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L, 1.0f, Runnable::run, location -> {
                });
                break;
            case 12:
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L, 1.0f, location -> {
                }, Looper.getMainLooper());
                break;
            case 13:
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L, 1.0f, location -> {
                });
                break;
            case 14:
                context.registerReceiver(mReceiver, new IntentFilter(DEMO_ACTION));
                break;
            case 15:
                context.unregisterReceiver(mReceiver);
                break;
            case 16:
                List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
                Log.e(TAG, "click " + installedPackages);
                break;
            case 17:
                List<ApplicationInfo> installedApplications = context.getPackageManager().getInstalledApplications(0);
                Log.e(TAG, "click " + installedApplications);
                break;
            case 18:
                Log.e(TAG, "click " + PendingIntent.getActivity(context, 1, new Intent(context, UriProxyActivity.class), PendingIntent.FLAG_IMMUTABLE));
                break;
            case 19:
                Log.e(TAG, "click " + PendingIntent.getActivity(context, 1, new Intent(context, UriProxyActivity.class), PendingIntent.FLAG_IMMUTABLE, new Bundle()));
                break;
            case 20:
                Log.e(TAG, "click " + PendingIntent.getActivities(context, 1, new Intent[]{new Intent(context, UriProxyActivity.class), new Intent(context, DemoLibActivity1.class)}, PendingIntent.FLAG_IMMUTABLE));
                break;
            case 21:
                Log.e(TAG, "click " + PendingIntent.getActivities(context, 1, new Intent[]{new Intent(context, UriProxyActivity.class), new Intent(context, DemoLibActivity1.class)}, PendingIntent.FLAG_IMMUTABLE, new Bundle()));
                break;
            case 22:
                Log.e(TAG, "click " + PendingIntent.getBroadcast(context, 1, new Intent("bbddsss"), PendingIntent.FLAG_IMMUTABLE));
                break;
            case 23:
                Log.e(TAG, "click " + PendingIntent.getForegroundService(context, 1, new Intent(context, DemoService.class), PendingIntent.FLAG_IMMUTABLE));
                break;
            case 24:
                Log.e(TAG, "click " + PendingIntent.getService(context, 1, new Intent(context, DemoService.class), PendingIntent.FLAG_IMMUTABLE));
                break;
        }
    }
}
