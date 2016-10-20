package com.example.alsaint.examplemvp.server;

import com.example.alsaint.examplemvp.server.models.CakeObj;

import retrofit2.Response;
import retrofit2.http.GET;
import rx.Observable;

public interface ServerApi {

    String BASE_URL = "https://gist.githubusercontent.com";

    @GET("/filippella/a728a34822a3bc7add98e477a4057b69/raw/310d712e87941f569074a63fedb675d2b611342a/cakes")
    Observable<Response<CakeObj>> getCakes();
}
