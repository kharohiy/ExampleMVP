package com.example.alsaint.examplemvp.application.modules;

import android.content.Context;

import com.example.alsaint.examplemvp.database.LocalStorage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class AppModule {

    private final Context context;
    private final ExecutorService executorService;

    public AppModule(Context context) {
        this(context, Executors.newCachedThreadPool());
    }

    public AppModule(Context context, ExecutorService executor) {
        this.context = context;
        this.executorService = executor;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    LocalStorage provideLocalStorage(Context context) {
        return new LocalStorage();
    }

    @Provides
    @Singleton
    ExecutorService provideExecutor() {
        return executorService;
    }
}