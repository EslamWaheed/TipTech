package com.tiptech.android.ui.fragment.quiz;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.tiptech.android.data.DataManager;
import com.tiptech.android.model.quiz.QuizResponse;
import com.tiptech.android.ui.base.BasePresenterIMP;
import com.tiptech.android.utils.rx.SchedulerProvider;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class QuizPresenterIMP<V extends QuizView> extends BasePresenterIMP<V> implements QuizPresenter<V> {
    private static final String TAG = "QuizPresenterIMP";

    @Inject
    public QuizPresenterIMP(DataManager mDataManager, CompositeDisposable mCompositeDisposable, SchedulerProvider mSchedulerProvider) {
        super(mDataManager, mCompositeDisposable, mSchedulerProvider);
    }

    @Override
    public void getQuiz() {
        getBaseView().showFullLoading();
        getCompositeDisposable().add(getDataManager().GetQuiz()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<QuizResponse>() {
                               @Override
                               public void accept(QuizResponse quizResponse) {
                                   getBaseView().hideFullLoading();
                                   getBaseView().showQuiz(quizResponse);
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) {
                                   getBaseView().hideFullLoading();
                                   QuizResponse errorParser = null;
                                   if (throwable instanceof HttpException) {
                                       ResponseBody body = ((HttpException) throwable).response().errorBody();
                                       Gson gson = new Gson();
                                       TypeAdapter<QuizResponse> adapter = gson.getAdapter(QuizResponse.class);
                                       try {
                                           errorParser = adapter.fromJson(body.string());
                                           getBaseView().showQuizError(errorParser.getErrors().get(0));
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
