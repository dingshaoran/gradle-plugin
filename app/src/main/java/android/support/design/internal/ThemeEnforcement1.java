package android.support.design.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;

public class ThemeEnforcement1 extends ThemeEnforcement {

    private final static String TAG = "ThemeEnforcement1";

    public static TypedArray obtainStyledAttributesStatic(Context context, AttributeSet set, int[] attrs, int defStyleAttr, int defStyleRes, int... textAppearanceResIndices) {
        Log.i(TAG, "obtainStyledAttributesStatic " + context + set + attrs + defStyleAttr + " " + defStyleRes + textAppearanceResIndices);
        throw new RuntimeException();
    }

    public TypedArray obtainStyledAttributes(Context context, AttributeSet set, int[] attrs, int defStyleAttr, int defStyleRes, int... textAppearanceResIndices) {
        Log.i(TAG, "obtainStyledAttributes " + context + set + attrs + defStyleAttr + " " + defStyleRes + textAppearanceResIndices);
        throw new RuntimeException();
    }

    public static boolean obtainBooleanStatic(Context context, AttributeSet set, int[] attrs, int defStyleAttr, int defStyleRes, int... textAppearanceResIndices) {
        Log.i(TAG, "obtainBooleanStatic " + context);
        throw new RuntimeException();
    }

    public boolean obtainBoolean(Context context, AttributeSet set, int[] attrs, int defStyleAttr, int defStyleRes, int... textAppearanceResIndices) {
        Log.i(TAG, "obtainBoolean " + context);
        throw new RuntimeException();
    }

    public static long obtainLongStatic(Context context, AttributeSet set, int[] attrs, int defStyleAttr, int defStyleRes, int... textAppearanceResIndices) {
        Log.i(TAG, "obtainLongStatic " + context + set + attrs + defStyleAttr + " " + defStyleRes + textAppearanceResIndices);
        return 12345567889L;
    }

    public long obtainLong(Context context, AttributeSet set, int[] attrs, int defStyleAttr, int defStyleRes, int... textAppearanceResIndices) {
        Log.i(TAG, "obtainLong " + context + set + attrs + defStyleAttr + " " + defStyleRes + textAppearanceResIndices);
        return 1;
    }

    public static void obtainVoidStatic(Context context, AttributeSet set, int[] attrs, int defStyleAttr, int defStyleRes, int... textAppearanceResIndices) {
        Log.i(TAG, "obtainVoidStatic " + context + set + attrs + defStyleAttr + " " + defStyleRes + textAppearanceResIndices);
    }

    public void obtainVoid(Context context, AttributeSet set, int[] attrs, int defStyleAttr, int defStyleRes, int... textAppearanceResIndices) {
        Log.i(TAG, "obtainVoid " + context + set + attrs + defStyleAttr + " " + defStyleRes + textAppearanceResIndices);
    }
}
