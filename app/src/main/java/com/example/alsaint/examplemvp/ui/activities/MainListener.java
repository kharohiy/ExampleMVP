package com.example.alsaint.examplemvp.ui.activities;

import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

public interface MainListener {

    void addFragment(Fragment fragment, String tag);

    void onBackPressed();

    void setToolbarTitle(String title);

    void setToolbarTitle(@StringRes int title);
}