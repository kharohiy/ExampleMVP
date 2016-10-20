package com.example.alsaint.examplemvp.ui.presenters;

public interface Presenter<V> {
    void onAttachView(V view);

    void onDetachView();
}