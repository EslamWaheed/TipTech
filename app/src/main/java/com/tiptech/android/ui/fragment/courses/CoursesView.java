package com.tiptech.android.ui.fragment.courses;

import com.tiptech.android.model.courses.CoursesResponse;
import com.tiptech.android.ui.base.BaseView;

public interface CoursesView extends BaseView {
    void showCourses(CoursesResponse coursesResponse);

    void showCoursesError(String error);
}
