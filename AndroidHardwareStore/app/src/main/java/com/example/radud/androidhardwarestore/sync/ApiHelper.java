package com.example.radud.androidhardwarestore.sync;

import android.util.Log;

import com.example.radud.androidhardwarestore.utils.UIUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;

import retrofit.Profiler;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

public class ApiHelper {

    private static final String ENDPOINT = ApiConstants.LOCALHOST_IP; //Connecting through
    private static ApiHelper sInstance;

    private HardwareStoreApi mHardwareStoreApi;

    private Gson mGson;

    public static ApiHelper getInstance() {
        if (sInstance == null) {
            sInstance = new ApiHelper();
        }

        return sInstance;
    }

    public static HardwareStoreApi getApi() {
        return getInstance().getCityCardApi();
    }

    private ApiHelper() {
        OkHttpClient okHttpClient = new OkHttpClient();

        File cacheDir = new File(UIUtils.getAppContext().getCacheDir().getAbsolutePath(),
                UIUtils.getAppContext().getPackageName());
        Cache cache = new Cache(cacheDir, 10 * 1024 * 1024);
        okHttpClient.setCache(cache);

        mGson = buildGson();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .setClient(new OkClient(okHttpClient))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(mGson))
                .setProfiler(new Profiler() {
                    @Override
                    public Object beforeCall() {
                        return null;
                    }

                    @Override
                    public void afterCall(RequestInformation requestInfo, long elapsedTime, int statusCode, Object beforeCallData) {
                        Log.d("ApiHelper", "[Call completed] time: " + elapsedTime + " code: " + statusCode
                                + " url: " + requestInfo.getBaseUrl() + requestInfo.getRelativePath());
                    }
                }).build();

        mHardwareStoreApi = restAdapter.create(HardwareStoreApi.class);
    }

    private HardwareStoreApi getCityCardApi() {
        return mHardwareStoreApi;
    }

    public String getApiUrl() {
        return ENDPOINT;
    }

    private Gson buildGson() {
        return new GsonBuilder()
                .disableHtmlEscaping()
                .create();
    }

    public Gson getGson() {
        return mGson;
    }
}