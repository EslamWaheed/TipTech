package com.tiptech.android.data;

import android.content.Context;

import com.tiptech.android.api.ApiHelper;
import com.tiptech.android.data.prefs.PreferencesHelper;
import com.tiptech.android.di.ApplicationContext;
import com.tiptech.android.model.courses.CoursesResponse;
import com.tiptech.android.model.news.NewsResponse;
import com.tiptech.android.model.quiz.QuizResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class DataManagerIMP implements DataManager {
    private static final String TAG = "DataManagerIMP";
    private final Context mContext;
    private final PreferencesHelper mPreferencesHelper;
    private ApiHelper apiHelper;

    @Inject
    DataManagerIMP(@ApplicationContext Context context, PreferencesHelper preferencesHelper, ApiHelper apiHelper) {
        this.mContext = context;
        this.mPreferencesHelper = preferencesHelper;
        this.apiHelper = apiHelper;
    }

    @Override
    public Single<CoursesResponse> GetCourse() {
        return apiHelper.GetCourse();
    }

    @Override
    public Single<NewsResponse> GetNew() {
        return apiHelper.GetNew();
    }

    @Override
    public Single<QuizResponse> GetQuiz() {
        return apiHelper.GetQuiz();
    }
}
