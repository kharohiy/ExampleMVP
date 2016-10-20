package com.example.alsaint.examplemvp.ui.dialogs;

import android.support.v4.app.DialogFragment;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BaseDialog extends DialogFragment {

    protected Unbinder unbinder;

    @Override
    public void onStart() {
        super.onStart();
        unbinder = ButterKnife.bind(this, getDialog());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    protected String getText(TextView textView) {
        return textView.getText().toString().trim();
    }
}