package com.example.alsaint.examplemvp.server;

public class ServerApiThrowable extends Throwable {

    private int statusCode;

    public ServerApiThrowable(String message, int statusCode) {
        super(message);

        this.statusCode = statusCode;
    }

    public int getErrorCode() {
        return statusCode;
    }
}