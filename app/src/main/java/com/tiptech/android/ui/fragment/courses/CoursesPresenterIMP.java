package com.tiptech.android.ui.fragment.courses;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.tiptech.android.data.DataManager;
import com.tiptech.android.model.courses.CoursesResponse;
import com.tiptech.android.ui.base.BasePresenterIMP;
import com.tiptech.android.utils.rx.SchedulerProvider;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class CoursesPresenterIMP<V extends CoursesView> extends BasePresenterIMP<V> implements CoursesPresenter<V> {
    private static final String TAG = "CoursesPresenterIMP";

    @Inject
    public CoursesPresenterIMP(DataManager mDataManager, CompositeDisposable mCompositeDisposable, SchedulerProvider mSchedulerProvider) {
        super(mDataManager, mCompositeDisposable, mSchedulerProvider);
    }

    @Override
    public void getCourses() {
        getBaseView().showFullLoading();
        getCompositeDisposable().add(getDataManager().GetCourse()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<CoursesResponse>() {
                               @Override
                               public void accept(CoursesResponse coursesResponse) {
                                   getBaseView().hideFullLoading();
                                   getBaseView().showCourses(coursesResponse);
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) {
                                   getBaseView().hideFullLoading();
                                   CoursesResponse errorParser = null;
                                   if (throwable instanceof HttpException) {
                                       ResponseBody body = ((HttpException) throwable).response().errorBody();
                                       Gson gson = new Gson();
                                       TypeAdapter<CoursesResponse> adapter = gson.getAdapter(CoursesResponse.class);
                                       try {
                                           errorParser = adapter.fromJson(body.string());
                                           getBaseView().showCoursesError(errorParser.getErrors().get(0));
                                           Log.i(TAG, "Error:" + errorParser.getErrors().get(0));
                                       } catch (IOException e) {
                                           e.printStackTrace();
                                       }
                                   }
                                   Log.d(TAG, "accept: throwable error is: " + throwable.getLocalizedMessage());
                               }
                           }
                )
        );
    }
}
