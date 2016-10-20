package com.example.alsaint.examplemvp.server;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.alsaint.examplemvp.R;
import com.example.alsaint.examplemvp.server.models.CakeObj;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Singleton
public class AppServerApi {

    private final Context context;
    private final ServerApi serverApi;

    private final String internetConnectionError;

    @Inject
    public AppServerApi(Context context, ServerApi serverApi) {
        this.context = context;
        this.serverApi = serverApi;

        this.internetConnectionError = context.getString(R.string.error_connection);
    }

    public Observable<CakeObj> getCakes() {
        if (!isOnline()) {
            return Observable.error(new ServerApiThrowable(internetConnectionError, ServerCodes.REQUEST_TIMED_OUT));
        }

        return serverApi
                .getCakes()
                .compose(applySchedulers())
                .flatMap(response -> {
                    if (!response.isSuccessful()) {
                        return Observable.error(new ServerApiThrowable("", response.code()));
                    } else {
                        // TODO save to database

                        return Observable.just(response.body());
                    }
                });
    }

    public boolean isOnline() {
        return isOnline(context);
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnectedOrConnecting());
    }

    private static <T> Observable.Transformer<T, T> applySchedulers() {
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
