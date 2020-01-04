package com.tiptech.android.ui.activity.main;

import com.tiptech.android.ui.base.BasePresenter;

public interface MainPresenter<V extends MainView> extends BasePresenter<V> {
    void onNavigationClick(int id);
}
