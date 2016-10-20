package com.example.alsaint.examplemvp.application.modules;


import android.content.Context;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.alsaint.examplemvp.server.ServerApi;
import com.google.gson.Gson;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

@Module
public final class ApiModule {

    /**
     * 10MB cache size.
     */
    private static final int DISK_CACHE_SIZE = 10 * 1024 * 1024;

    /**
     * requests timeout.
     */
    private static final int TIMEOUT = 30;

    @Provides
    @Singleton
    Gson providesGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    Glide providesGlide(Context context) {
        return Glide.get(context);
    }

    @Provides
    @Singleton
    ServerApi provideServerApi(OkHttpClient client, ExecutorService executor, Gson gson) {
        return toServerApi(client, executor, gson).create(ServerApi.class);
    }

    @Provides
    @Singleton
    OkHttpClient provideHttpClient(Context context) {
        File cacheDir = new File(context.getCacheDir(), "cached");
        Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> Log.d("OkHttp", message));
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .cache(cache)
                .build();
    }

    private Retrofit toServerApi(OkHttpClient client, ExecutorService executor, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(ServerApi.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.from(executor)))
                .callbackExecutor(executor)
                .build();
    }
}