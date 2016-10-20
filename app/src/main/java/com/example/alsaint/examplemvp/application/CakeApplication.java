package com.example.alsaint.examplemvp.application;

import android.app.Application;
import android.content.Context;

import com.example.alsaint.examplemvp.BuildConfig;
import com.example.alsaint.examplemvp.application.components.AppComponent;
import com.example.alsaint.examplemvp.application.components.DaggerAppComponent;
import com.example.alsaint.examplemvp.application.modules.ApiModule;
import com.example.alsaint.examplemvp.application.modules.AppModule;

import java.util.concurrent.Executors;

import io.paperdb.Paper;
import timber.log.Timber;

public class CakeApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .appModule(new AppModule(this, Executors.newSingleThreadExecutor()))
                .build();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            //Fabric.with(this, new Crashlytics());
        }

        Paper.init(this);
    }

    public static AppComponent getComponent(Context context) {
        return ((CakeApplication) context.getApplicationContext()).appComponent;
    }
}