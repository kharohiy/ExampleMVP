package com.example.alsaint.examplemvp.ui.activities;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.alsaint.examplemvp.R;
import com.example.alsaint.examplemvp.ui.fragments.CakeFragment;

public class MainActivity extends AppCompatActivity implements MainListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fragment_container, new CakeFragment());
        transaction.commit();
    }

    @Override
    public void addFragment(Fragment fragment, String tag) {

    }

    @Override
    public void setToolbarTitle(String title) {

    }

    @Override
    public void setToolbarTitle(@StringRes int title) {

    }
}
