package com.example.alsaint.examplemvp.ui.presenters;

import android.content.Context;

import com.example.alsaint.examplemvp.server.AppServerApi;
import com.example.alsaint.examplemvp.server.models.CakeObj;
import com.example.alsaint.examplemvp.ui.fragments.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import timber.log.Timber;

public class CakePresenter extends FragmentPresenter<CakePresenter.MvpView> {

    private final AppServerApi serverApi;

    @Inject
    public CakePresenter(Context context, AppServerApi serverApi) {
        super(context);

        this.serverApi = serverApi;
    }

    public void getCakes() {
        mvpView.showProgress(true);

        Subscription subscription = serverApi
                .getCakes()
                .subscribe(clients -> {
                    mvpView.showProgress(false);

                    mvpView.initAdapter(clients.getCakes());

                }, throwable -> {
                    Timber.e(throwable, "cannot load cakes");
                    mvpView.showProgress(false);
                    mvpView.showError(throwable);
                });
        addSubscription(subscription);
    }

    public interface MvpView extends BaseFragment.BaseMvpView {
        void initAdapter(List<CakeObj.Cake> clients);
    }
}