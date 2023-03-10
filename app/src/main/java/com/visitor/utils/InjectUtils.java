package com.visitor.utils;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.location.Criteria;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.support.design.internal.ThemeEnforcement;
import android.util.AttributeSet;
import android.util.Log;

import com.visitor.DemoApplication;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

import okhttp3.OkHttpClient;

public class InjectUtils {

    private static final String TAG = "InjectUtils";

    public static void attachBaseContext(Callable<Void> callable, DemoApplication application, Context base) throws Exception {
        Log.i("aaaa", "bbbbb");
        callable.call();
    }

    public static void demoAnno(Callable<Void> callable, Object obj, String base) throws Exception {
        Log.i("aaaa", "bbbbb" + obj);
        callable.call();
    }

    public static String aaaaaaaaaaaaaaaaa(Object obj) throws Exception {
//        Log.i("aaaa", "bbbbb " + obj.getClass() + base);
        return "bbbbbbbbbbbb";
    }

    public static String aaaaaaaaaaaaaaaaa() throws Exception {
//        Log.i("aaaa", "bbbbb " + obj.getClass() + base);
        return "bbbbbbbbbbbb1";
    }


    public static TypedArray obtainStyledAttributesStatic(Callable<TypedArray> callable, Context context, AttributeSet set, int[] attrs, int defStyleAttr, int defStyleRes, int... textAppearanceResIndices) {
        Log.i(TAG, "obtainStyledAttributesStatic " + context + set + attrs + defStyleAttr + " " + defStyleRes + textAppearanceResIndices);
        return context.obtainStyledAttributes(set, attrs, defStyleAttr, defStyleRes);
    }

    public static TypedArray obtainStyledAttributes(Callable<TypedArray> callable, ThemeEnforcement enforcement, Context context, AttributeSet set, int[] attrs, int defStyleAttr, int defStyleRes, int... textAppearanceResIndices) {
        try {
            TypedArray call = callable.call();
            Log.i(TAG, "obtainStyledAttributes " + context + set + attrs + defStyleAttr + " " + defStyleRes + textAppearanceResIndices);
            return call;
        } catch (Exception e) {
            Log.i(TAG, "obtainStyledAttributes " + context + set + attrs + defStyleAttr + " " + defStyleRes + textAppearanceResIndices + e);
            return null;
        }
    }

    public static boolean obtainBooleanStatic(Callable<Boolean> callable, Context context, AttributeSet set, int[] attrs, int defStyleAttr, int defStyleRes, int... textAppearanceResIndices) {
        Log.i(TAG, "obtainBooleanStatic " + context + set + attrs + defStyleAttr + " " + defStyleRes + textAppearanceResIndices);
        return true;
    }

    public static boolean obtainBoolean(Callable<Boolean> callable, ThemeEnforcement enforcement, Context context, AttributeSet set, int[] attrs, int defStyleAttr, int defStyleRes, int... textAppearanceResIndices) {
        try {
            return callable.call();
        } catch (Exception e) {
            Log.i(TAG, "obtainBoolean " + context + set + attrs + defStyleAttr + " " + defStyleRes + textAppearanceResIndices + e);
            return true;
        }
    }

    public static long obtainLongStatic(Callable<Long> callable, Context context, AttributeSet set, int[] attrs, int defStyleAttr, int defStyleRes, int... textAppearanceResIndices) throws Exception {
        try {
            Long call = callable.call();
            Log.i(TAG, "obtainLongStatic " + context + set + attrs + defStyleAttr + " " + defStyleRes + textAppearanceResIndices);
            return call;
        } catch (Exception e) {
            Log.i(TAG, "obtainLongStatic " + context + set + attrs + defStyleAttr + " " + defStyleRes + textAppearanceResIndices + e);
            throw e;
        }
    }

    public static long obtainLong(Callable<Long> callable, ThemeEnforcement enforcement, Context context, AttributeSet set, int[] attrs, int defStyleAttr, int defStyleRes, int... textAppearanceResIndices) throws Exception {
        Log.i(TAG, "obtainLong " + context + set + attrs + defStyleAttr + " " + defStyleRes + textAppearanceResIndices);
        return 11111;
    }

    public static void obtainVoidStatic(Callable<Void> callable, Context context, AttributeSet set, int[] attrs, int defStyleAttr, int defStyleRes, int... textAppearanceResIndices) throws Exception {
        try {
            callable.call();
            Log.i(TAG, "obtainVoidStatic " + context + set + attrs + defStyleAttr + " " + defStyleRes + textAppearanceResIndices);
        } catch (Exception e) {
            Log.i(TAG, "obtainVoidStatic " + context + set + attrs + defStyleAttr + " " + defStyleRes + textAppearanceResIndices + e);
            throw e;
        }
    }

    public static void obtainVoid(Callable<Void> callable, ThemeEnforcement enforcement, Context context, AttributeSet set, int[] attrs, int defStyleAttr, int defStyleRes, int... textAppearanceResIndices) throws Exception {
        try {
            callable.call();
            Log.i(TAG, "obtainVoid " + context + set + attrs + defStyleAttr + " " + defStyleRes + textAppearanceResIndices);
        } catch (Exception e) {
            Log.i(TAG, "obtainVoid " + context + set + attrs + defStyleAttr + " " + defStyleRes + textAppearanceResIndices + e);
            throw e;
        }
    }


    public static void copyPixelsToBuffer(Bitmap bitmap, Buffer buffer) {
        Log.i(TAG, "copyPixelsToBuffer " + bitmap + buffer);
    }

    public static OkHttpClient build(OkHttpClient.Builder builder) {
        Log.i(TAG, "OkHttpClient " + builder);
        return builder.build();
    }

    public static int i(String tag, String msg) {
        Log.i(TAG, "i " + tag + msg);
        return Log.i(tag, msg);
    }

    public static int d(String tag, String msg) {
        Log.i(TAG, "d " + tag + msg);
        return Log.i(tag, msg);
    }

    public static int w(String tag, String msg) {
        Log.i(TAG, "w " + tag + msg);
        return Log.i(tag, msg);
    }

    public static int v(String tag, String msg) {
        Log.i(TAG, "v " + tag + msg);
        return Log.i(tag, msg);
    }

    public static int e(String tag, String msg) {
        Log.i(TAG, "e " + tag + msg);
        return Log.i(tag, msg);
    }

    public static int println(int level, String tag, String msg) {
        Log.i(TAG, "println " + level + tag + msg);
        return Log.println(level, tag, msg);
    }

    @SuppressLint("MissingPermission")
    public static void requestSingleUpdate(LocationManager lm, String provider, LocationListener listener, Looper looper) {
        Log.i(TAG, "requestSingleUpdate " + provider + listener + looper);
        try {
            lm.requestSingleUpdate(provider, listener, looper);
        } catch (Exception e) {
        }
    }

    @SuppressLint("MissingPermission")
    public static void requestSingleUpdate(LocationManager lm, Criteria criteria, LocationListener locationListener, Looper looper) {
        Log.i(TAG, "requestSingleUpdate " + criteria + locationListener + looper);
        try {
            lm.requestSingleUpdate(criteria, locationListener, looper);
        } catch (Exception e) {
        }
    }

    @SuppressLint("MissingPermission")
    public static void requestLocationUpdates(LocationManager lm, String provider, long minTimeMs, float minDistanceM, Executor executor, LocationListener listener) {
        Log.i(TAG, "requestLocationUpdates " + provider + executor + listener);
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                lm.requestLocationUpdates(provider, minTimeMs, minDistanceM, executor, listener);
            }
        } catch (Exception e) {
        }
    }

    @SuppressLint("MissingPermission")
    public static void requestLocationUpdates(LocationManager lm, String provider, long minTimeMs, float minDistanceM, LocationListener listener, Looper looper) {
        Log.i(TAG, "requestLocationUpdates " + provider + listener + looper);
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                lm.requestLocationUpdates(provider, minTimeMs, minDistanceM, listener, looper);
            }
        } catch (Exception e) {
        }
    }

    @SuppressLint("MissingPermission")
    public static void requestLocationUpdates(LocationManager lm, String provider, long minTimeMs, float minDistanceM, LocationListener listener) {
        Log.i(TAG, "requestLocationUpdates " + provider + minTimeMs + " " + minDistanceM + listener);
        try {
            lm.requestLocationUpdates(provider, minTimeMs, minDistanceM, listener);
        } catch (Exception e) {
        }
    }

    public static Intent registerReceiver(Context context, BroadcastReceiver receiver, IntentFilter filter) {
        Log.i(TAG, "registerReceiver " + receiver + filter);
        return context.registerReceiver(receiver, filter);
    }

    public static void unregisterReceiver(Context context, BroadcastReceiver receiver) {
        Log.i(TAG, "unregisterReceiver " + receiver);
        try {
            context.unregisterReceiver(receiver);
        } catch (Exception e) {
        }
    }

    public static List<PackageInfo> getInstalledPackages(PackageManager pm, int flag) {
        Log.i(TAG, "getInstalledPackages " + pm + flag);
        try {
            return pm.getInstalledPackages(flag);
        } catch (Exception e) {
        }
        return new ArrayList<>();
    }

    public static List<ApplicationInfo> getInstalledApplications(PackageManager pm, int flag) {
        Log.i(TAG, "getInstalledApplications " + pm + flag);
        try {
            return pm.getInstalledApplications(flag);
        } catch (Exception e) {
        }
        return new ArrayList<>();
    }

    public static PendingIntent getActivity(Context c, int requestCode, Intent intent, int flag) {
        Log.i(TAG, "getActivity " + c + requestCode + intent + flag);
        return PendingIntent.getActivity(c, requestCode, intent, flag);
    }

    public static PendingIntent getActivity(Context c, int requestCode, Intent intent, int flag, Bundle bundle) {
        Log.i(TAG, "getActivity " + c + requestCode + intent + flag + bundle);
        return PendingIntent.getActivity(c, requestCode, intent, flag, bundle);
    }

    public static PendingIntent getActivities(Context c, int requestCode, Intent[] intent, int flag) {
        Log.i(TAG, "getActivities " + c + requestCode + intent + flag);
        return PendingIntent.getActivities(c, requestCode, intent, flag);
    }

    public static PendingIntent getActivities(Context c, int requestCode, Intent[] intent, int flag, Bundle bundle) {
        Log.i(TAG, "getActivities " + c + requestCode + intent + flag + bundle);
        return PendingIntent.getActivities(c, requestCode, intent, flag, bundle);
    }

    public static PendingIntent getBroadcast(Context c, int requestCode, Intent intent, int flag) {
        Log.i(TAG, "getBroadcast " + c + requestCode + intent + flag);
        return PendingIntent.getBroadcast(c, requestCode, intent, flag);
    }

    public static PendingIntent getForegroundService(Context c, int requestCode, Intent intent, int flag) {
        Log.i(TAG, "getForegroundService " + c + requestCode + intent + flag);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return PendingIntent.getForegroundService(c, requestCode, intent, flag);
        } else return null;
    }

    public static PendingIntent getService(Context c, int requestCode, Intent intent, int flag) {
        Log.i(TAG, "getService " + c + requestCode + intent + flag);
        return PendingIntent.getService(c, requestCode, intent, flag);
    }
}
