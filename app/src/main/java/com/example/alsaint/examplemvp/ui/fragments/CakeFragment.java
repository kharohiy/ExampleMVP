package com.example.alsaint.examplemvp.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alsaint.examplemvp.R;
import com.example.alsaint.examplemvp.application.CakeApplication;
import com.example.alsaint.examplemvp.server.models.CakeObj;
import com.example.alsaint.examplemvp.ui.adatpers.CakeAdapter;
import com.example.alsaint.examplemvp.ui.presenters.CakePresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CakeFragment extends BaseFragment implements CakePresenter.MvpView  {

    @BindView(R.id.cake_list)
    RecyclerView recyclerView;
    @BindView(R.id.cake_empty_text)
    View emptyView;

    @Inject
    CakePresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.screen_cake, container, false);

        CakeApplication
                .getComponent(getContext())
                .inject(this);

        presenter.onAttachView(this, ButterKnife.bind(this, view));

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        presenter.getCakes();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        presenter.onDetachView();
    }

    @Override
    public void initAdapter(List<CakeObj.Cake> cakes) {
        CakeAdapter adapter = new CakeAdapter(getContext(), cakes, getLayoutInflater(null));
        recyclerView.setAdapter(adapter);

        showEmptyView(cakes.isEmpty());
    }

    private void showEmptyView(boolean isEmpty) {
        recyclerView.setVisibility(isEmpty ? View.INVISIBLE : View.VISIBLE);
        emptyView.setVisibility(isEmpty ? View.VISIBLE : View.INVISIBLE);
    }
}
