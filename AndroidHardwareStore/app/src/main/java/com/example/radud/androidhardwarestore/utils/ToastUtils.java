package com.example.radud.androidhardwarestore.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

    public static void showError(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
