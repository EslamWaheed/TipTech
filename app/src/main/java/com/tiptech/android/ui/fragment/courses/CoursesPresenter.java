package com.tiptech.android.ui.fragment.courses;

import com.tiptech.android.ui.base.BasePresenter;

public interface CoursesPresenter<V extends CoursesView> extends BasePresenter<V> {
    void getCourses();
}
