package com.tiptech.android.ui.fragment.news;

import com.tiptech.android.model.news.NewsResponse;
import com.tiptech.android.ui.base.BaseView;

public interface NewsView extends BaseView {
    void showNews(NewsResponse newsResponse);

    void showNewsError(String error);
}
