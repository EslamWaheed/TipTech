package com.tiptech.android.ui.fragment.home;

import com.tiptech.android.model.courses.CoursesResponse;
import com.tiptech.android.model.news.NewsResponse;
import com.tiptech.android.ui.base.BaseView;

public interface HomeView extends BaseView {
    void showCourses(CoursesResponse coursesResponse);

    void showCoursesError(String error);

    void showNews(NewsResponse newsResponse);

    void showNewsError(String error);
}
