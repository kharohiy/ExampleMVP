package com.example.alsaint.examplemvp.ui.dialogs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.alsaint.examplemvp.R;
import com.example.alsaint.examplemvp.server.ServerCodes;

public class ErrorCodeDialog extends ErrorDialog {

    public static final String TAG = ErrorCodeDialog.class.getSimpleName();

    private static final String ARG_ERROR_CODE = "com.example.alsaint.examplemvp.ui.dialogs.ARG_ERROR_CODE";

    public static DialogFragment newInstance(int code) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_ERROR_CODE, code);

        DialogFragment fragment = new ErrorCodeDialog();
        fragment.setArguments(bundle);

        return fragment;
    }

    @NonNull
    @Override
    public AlertDialog onCreateDialog(Bundle savedInstanceState) {

        int errorCode = getArguments().getInt(ARG_ERROR_CODE);
        String message = new ServerCodes.Message(getContext()).getText(errorCode);

        return new AlertDialog.Builder(getContext())
                .setTitle(R.string.app_error)
                .setMessage(message)
                .setNeutralButton(R.string.app_ok, (dialogInterface, i) -> {
                    switch (errorCode) {
                        case 401:
                            // TODO logout();
                            break;
                    }
                    dismiss();
                })
                .create();
    }
}