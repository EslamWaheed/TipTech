package com.tiptech.android.ui.fragment.news;

import com.tiptech.android.ui.base.BasePresenter;

public interface NewsPresenter<V extends NewsView> extends BasePresenter<V> {
    void getNews();
}
