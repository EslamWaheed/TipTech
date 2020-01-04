package com.tiptech.android.ui.fragment.home;

import com.tiptech.android.ui.base.BasePresenter;

public interface HomePresenter<V extends HomeView> extends BasePresenter<V> {
    void getCourses();

    void getNews();

}
