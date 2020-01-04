package com.tiptech.android.ui.base;


import com.tiptech.android.data.DataManager;
import com.tiptech.android.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenterIMP<V extends BaseView> implements BasePresenter<V> {
    private static final String TAG = "BasePresenterIMP";
    private final DataManager mDataManager;
    private final CompositeDisposable mCompositeDisposable;
    private final SchedulerProvider mSchedulerProvider;

    private V mMvpView;

    @Inject
    public BasePresenterIMP(DataManager mDataManager, CompositeDisposable mCompositeDisposable, SchedulerProvider mSchedulerProvider) {
        this.mDataManager = mDataManager;
        this.mCompositeDisposable = mCompositeDisposable;
        this.mSchedulerProvider = mSchedulerProvider;
    }

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void onDetach() {
        mCompositeDisposable.dispose();
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public V getBaseView() {
        return mMvpView;
    }


    public DataManager getDataManager() {
        return mDataManager;
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

}
