package com.tiptech.android.data;


import com.tiptech.android.model.courses.CoursesResponse;
import com.tiptech.android.model.news.NewsResponse;
import com.tiptech.android.model.quiz.QuizResponse;

import io.reactivex.Single;

public interface DataManager {
    Single<CoursesResponse> GetCourse();

    Single<NewsResponse> GetNew();

    Single<QuizResponse> GetQuiz();
}
