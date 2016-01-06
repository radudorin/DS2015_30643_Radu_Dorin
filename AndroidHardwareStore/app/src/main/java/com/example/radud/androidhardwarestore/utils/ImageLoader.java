package com.example.radud.androidhardwarestore.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageLoader {

    public static void loadImage(ImageView view, String url) {
        Glide.with(UIUtils.getAppContext())
                .load(url)
                .into(view);
    }

    public static void loadImageWithPlaceHolder(ImageView view, String url, int drawable) {
        Glide.with(UIUtils.getAppContext())
                .load(url)
                .placeholder(drawable)
                .into(view);
    }

    public static void loadImageCenterCrop(ImageView view, String url) {
        Glide.with(UIUtils.getAppContext())
                .load(url)
                .centerCrop()
                .into(view);
    }

    public static void loadImageWithBlur(ImageView view, String url) {
        int width = view.getWidth() != 0 ? view.getWidth() / 10 : 100;
        int height = view.getHeight() != 0 ? view.getHeight() / 10 : 65;
        Glide.with(UIUtils.getAppContext())
                .load(url)
                .override(width, height)
                .into(view);
    }
}
