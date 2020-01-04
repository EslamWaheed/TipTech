package com.tiptech.android.api;

import com.tiptech.android.model.courses.CoursesResponse;
import com.tiptech.android.model.news.NewsResponse;
import com.tiptech.android.model.quiz.QuizResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ApiHelper {
    @GET("AppApi/GetCourse")
    Single<CoursesResponse> GetCourse();

    @GET("AppApi/GetNew")
    Single<NewsResponse> GetNew();

    @GET("AppApi/GetQuiz")
    Single<QuizResponse> GetQuiz();
}
