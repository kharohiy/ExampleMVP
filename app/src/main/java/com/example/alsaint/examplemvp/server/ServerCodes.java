package com.example.alsaint.examplemvp.server;

import android.content.Context;

import com.example.alsaint.examplemvp.R;

public final class ServerCodes {

    private ServerCodes() {
    }

    public static final int OK = 200;
    public static final int CREATED = 201;
    public static final int UNKNOWN_ERROR = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int REQUEST_TIMED_OUT = 408;

    public static boolean isValidatedStatus(int status) {
        return status < UNKNOWN_ERROR;
    }

    public static class Message {

        private final Context context;

        public Message(Context context) {
            this.context = context;
        }

        public String getText(int code) {
            final int textResource;

            switch (code) {
                case UNAUTHORIZED: {
                    textResource = R.string.error_session_has_expired;
                    break;
                }
                case REQUEST_TIMED_OUT: {
                    textResource = R.string.error_connection;
                    break;
                }
                case FORBIDDEN:
                    textResource = R.string.error_connection;
                    break;
                case NOT_FOUND:
                case UNKNOWN_ERROR:
                default: {
                    textResource = R.string.error_unknown;
                    break;
                }
            }
            return context.getResources().getString(textResource);
        }
    }
}