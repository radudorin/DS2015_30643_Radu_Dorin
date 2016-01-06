package com.example.radud.androidhardwarestore.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.radud.androidhardwarestore.model.Member;

public abstract class SessionUtils {
    private final static String SESSION_PREFERENCES = "user_sh_preferences";
    private static final String USER_ID = "user_id";

    private static final String USER_FULL_NAME = "USER_FULL_NAME";
    private static final String ROLE_ID = "ROLE_ID";


    private static Member sMember;

    public static SharedPreferences getSessionPreferences() {
        return HardwareStoreApp.getContext()
                .getSharedPreferences(SESSION_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static boolean isUserLoggedIn() {
        return getSessionPreferences().getInt(USER_ID, -1) != -1;
    }

    public static void saveUserID(int newValue) {
        getSessionPreferences().edit().putInt(USER_ID, newValue).commit();
    }

    public static int getUserId() {
        return getSessionPreferences().getInt(USER_ID, -1);
    }

    public static void saveUserROle(int newValue) {
        getSessionPreferences().edit().putInt(ROLE_ID, newValue).commit();
    }

    public static int getUserRole() {
        return getSessionPreferences().getInt(ROLE_ID, -1);
    }

    public static void saveUserFullName(String newValue) {
        getSessionPreferences().edit().putString(USER_FULL_NAME, newValue).commit();
    }

    public static String getUserFullName() {
        return getSessionPreferences().getString(USER_FULL_NAME, null);
    }

    public static void clearUserSession() {
        getSessionPreferences().edit().clear().apply();
    }
}