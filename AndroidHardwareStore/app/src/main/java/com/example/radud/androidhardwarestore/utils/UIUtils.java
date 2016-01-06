package com.example.radud.androidhardwarestore.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;


public abstract class UIUtils {
    public static int dpToPx(int dps) {
        return (int) getAppContext().getResources().getDisplayMetrics().density * dps;
    }

    public static int spToPx(int sps) {
        return (int) getAppContext().getResources().getDisplayMetrics().scaledDensity * sps;
    }

    public static int getDimension(int resId) {
        return (int) getAppContext().getResources().getDimension(resId);
    }

    public static int getColor(int colorId) {
        return getAppContext().getResources().getColor(colorId);
    }

    public static Resources getResources() {
        return getAppContext().getResources();
    }

    public static String getString(int stringId) {
        return getAppContext().getString(stringId);
    }

    public static Context getAppContext() {
        return HardwareStoreApp.getContext();
    }

    public static AssetManager getAssets() {
        return HardwareStoreApp.getContext().getAssets();
    }

    public static Point getScreenSize() {
        WindowManager wm = (WindowManager) UIUtils.getAppContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    public static void showKeyboard(EditText editText) {
        if (editText == null) return;

        editText.requestFocus();
        Context context = editText.getContext();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }

    public static void hideKeyboard(TextView textView) {
        if (textView == null) return;

        Context context = textView.getContext();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(textView.getWindowToken(), 0);
    }

    public static int getDarkenColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.8f;
        color = Color.HSVToColor(hsv);
        return color;
    }

    public static int parseColor(String color) {
        try {
            return Color.parseColor(color);
        } catch (Throwable ignored) {
        }

        return Color.BLACK;
    }
}
