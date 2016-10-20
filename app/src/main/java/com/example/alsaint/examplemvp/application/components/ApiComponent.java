package com.example.alsaint.examplemvp.application.components;

import com.bumptech.glide.Glide;
import com.example.alsaint.examplemvp.server.AppServerApi;
import com.google.gson.Gson;

public interface ApiComponent {

    AppServerApi appServerApi();

    Gson gson();

    Glide glide();
}