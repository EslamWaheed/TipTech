package com.tiptech.android.ui.activity.main;

import com.tiptech.android.data.DataManager;
import com.tiptech.android.ui.base.BasePresenterIMP;
import com.tiptech.android.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class MainPresenterIMP<V extends MainView> extends BasePresenterIMP<V> implements MainPresenter<V> {

    @Inject
    public MainPresenterIMP(DataManager mDataManager, CompositeDisposable mCompositeDisposable, SchedulerProvider mSchedulerProvider) {
        super(mDataManager, mCompositeDisposable, mSchedulerProvider);
    }

    @Override
    public void onNavigationClick(int id) {
        getBaseView().changeNavigationFragment(id);
    }
}
