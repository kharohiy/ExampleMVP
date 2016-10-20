package com.example.alsaint.examplemvp.application.components;

import com.example.alsaint.examplemvp.application.modules.ApiModule;
import com.example.alsaint.examplemvp.application.modules.AppModule;
import com.example.alsaint.examplemvp.database.LocalStorage;
import com.example.alsaint.examplemvp.ui.fragments.CakeFragment;

import java.util.concurrent.ExecutorService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        ApiModule.class
})
public interface AppComponent extends ApiComponent {

    void inject(CakeFragment fragment);

    LocalStorage localStorage();

    ExecutorService executorService();
}