package com.tiptech.android.ui.activity.splash;

import com.tiptech.android.data.DataManager;
import com.tiptech.android.ui.base.BasePresenterIMP;
import com.tiptech.android.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SplashPresenterIMP<V extends SplashView> extends BasePresenterIMP<V> implements SplashPresenter<V> {

    @Inject
    public SplashPresenterIMP(DataManager dataManager, CompositeDisposable compositeDisposable, SchedulerProvider schedulerProvider) {
        super(dataManager, compositeDisposable, schedulerProvider);
    }
}
