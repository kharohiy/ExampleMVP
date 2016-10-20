package com.example.alsaint.examplemvp.ui.dialogs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.alsaint.examplemvp.R;

public class ErrorDialog extends BaseDialog {

    public static final String TAG = ErrorDialog.class.getSimpleName();

    private static final String ARG_ERROR_MESSAGE = "com.example.alsaint.examplemvp.ui.dialogs.ARG_ERROR_MESSAGE";

    public static DialogFragment newInstance(String message) {
        Bundle bundle = new Bundle();
        bundle.putString(ARG_ERROR_MESSAGE, message);

        DialogFragment fragment = new ErrorDialog();
        fragment.setArguments(bundle);

        return fragment;
    }

    @NonNull
    @Override
    public AlertDialog onCreateDialog(Bundle savedInstanceState) {

        String message = getArguments().getString(ARG_ERROR_MESSAGE);
        if (message == null) {
            message = getString(R.string.error_unknown);
        }

        return new AlertDialog.Builder(getContext())
                .setTitle(R.string.app_error)
                .setMessage(message)
                .setNeutralButton(R.string.app_ok, (dialogInterface, i) -> {
                    dismiss();
                })
                .create();
    }
}