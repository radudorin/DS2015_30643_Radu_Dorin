package com.example.radud.androidhardwarestore.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class ResourceUtils {

    @Nullable
    @SuppressWarnings("deprecation")
    public static Drawable getDrawable(@NonNull Context context, @DrawableRes int drawableId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            return context.getDrawable(drawableId);

        //noinspection deprecation
        return context.getResources().getDrawable(drawableId);
    }

    @Nullable
    @SuppressWarnings("deprecation")
    public static int getcolor(@NonNull Context context, @ColorRes int colorId) {
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            return context.getResources().getColor(colorId, null);*/
        //TODO find out why this exception pops up, maybe platform bug ?

        //noinspection deprecation
        return context.getResources().getColor(colorId);
    }
}
