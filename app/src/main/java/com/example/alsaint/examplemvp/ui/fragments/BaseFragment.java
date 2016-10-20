package com.example.alsaint.examplemvp.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

import com.example.alsaint.examplemvp.R;
import com.example.alsaint.examplemvp.server.ServerApiThrowable;
import com.example.alsaint.examplemvp.ui.activities.MainActivity;
import com.example.alsaint.examplemvp.ui.activities.MainListener;
import com.example.alsaint.examplemvp.ui.dialogs.ErrorCodeDialog;
import com.example.alsaint.examplemvp.ui.dialogs.ErrorDialog;
import com.example.alsaint.examplemvp.ui.views.ProgressHelper;

public abstract class BaseFragment extends Fragment {

    private ProgressHelper progress;
    protected MainListener mainListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (getActivity() instanceof MainActivity) {
            mainListener = (MainListener) getActivity();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progress = new ProgressHelper(getContext());
    }

    public void showProgress(boolean show) {
        if (show) {
            progress.show(R.string.app_loading, true);
        } else {
            progress.hide();
        }
    }

    public void showError(Throwable throwable) {
        if (throwable instanceof ServerApiThrowable) {
            showError(((ServerApiThrowable) throwable).getErrorCode());
        } else {
            showError(throwable.getMessage());
        }
    }

    private void showError(int errorCode) {
        DialogFragment fragment = ErrorCodeDialog.newInstance(errorCode);
        fragment.show(getActivity().getSupportFragmentManager(), ErrorCodeDialog.TAG);
    }

    private void showError(String message) {
        DialogFragment fragment = ErrorDialog.newInstance(message);
        fragment.show(getActivity().getSupportFragmentManager(), ErrorDialog.TAG);
    }

    public void onBackPressed() {
        mainListener.onBackPressed();
    }

    public interface BaseMvpView {
        void showProgress(boolean show);

        void showError(Throwable throwable);

        void onBackPressed();
    }
}