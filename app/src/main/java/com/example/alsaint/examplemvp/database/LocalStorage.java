package com.example.alsaint.examplemvp.database;

import io.paperdb.Paper;

public class LocalStorage {

    public static final String USER_TOKEN = "com.example.alsaint.examplemvp.database.USER_TOKEN";

    public String getUserToken() {
        return Paper.book().read(USER_TOKEN, null);
    }

    public void setUserToken(String userToken) {
        Paper.book().write(USER_TOKEN, userToken);
    }
}