package com.tiptech.android.ui.fragment.news;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.tiptech.android.data.DataManager;
import com.tiptech.android.model.news.NewsResponse;
import com.tiptech.android.ui.base.BasePresenterIMP;
import com.tiptech.android.utils.rx.SchedulerProvider;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class NewsPresenterIMP<V extends NewsView> extends BasePresenterIMP<V> implements NewsPresenter<V> {
    private static final String TAG = "NewsPresenterIMP";

    @Inject
    public NewsPresenterIMP(DataManager mDataManager, CompositeDisposable mCompositeDisposable, SchedulerProvider mSchedulerProvider) {
        super(mDataManager, mCompositeDisposable, mSchedulerProvider);
    }

    @Override
    public void getNews() {
        getBaseView().showFullLoading();
        getCompositeDisposable().add(getDataManager().GetNew()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<NewsResponse>() {
                               @Override
                               public void accept(NewsResponse newsResponse) {
                                   getBaseView().hideFullLoading();
                                   getBaseView().showNews(newsResponse);
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) {
                                   getBaseView().hideFullLoading();
                                   NewsResponse errorParser = null;
                                   if (throwable instanceof HttpException) {
                                       ResponseBody body = ((HttpException) throwable).response().errorBody();
                                       Gson gson = new Gson();
                                       TypeAdapter<NewsResponse> adapter = gson.getAdapter(NewsResponse.class);
                                       try {
                                           errorParser = adapter.fromJson(body.string());
                                           getBaseView().showNewsError(errorParser.getErrors().get(0));
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
