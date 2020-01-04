package com.tiptech.android.di.module;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.tiptech.android.di.ActivityContext;
import com.tiptech.android.ui.activity.main.MainPresenter;
import com.tiptech.android.ui.activity.main.MainPresenterIMP;
import com.tiptech.android.ui.activity.main.MainView;
import com.tiptech.android.ui.activity.splash.SplashPresenter;
import com.tiptech.android.ui.activity.splash.SplashPresenterIMP;
import com.tiptech.android.ui.activity.splash.SplashView;
import com.tiptech.android.ui.base.BasePresenter;
import com.tiptech.android.ui.base.BasePresenterIMP;
import com.tiptech.android.ui.base.BaseView;
import com.tiptech.android.ui.fragment.courses.CoursesPresenter;
import com.tiptech.android.ui.fragment.courses.CoursesPresenterIMP;
import com.tiptech.android.ui.fragment.courses.CoursesView;
import com.tiptech.android.ui.fragment.home.HomePresenter;
import com.tiptech.android.ui.fragment.home.HomePresenterIMP;
import com.tiptech.android.ui.fragment.home.HomeView;
import com.tiptech.android.ui.fragment.news.NewsPresenter;
import com.tiptech.android.ui.fragment.news.NewsPresenterIMP;
import com.tiptech.android.ui.fragment.news.NewsView;
import com.tiptech.android.ui.fragment.quiz.QuizPresenter;
import com.tiptech.android.ui.fragment.quiz.QuizPresenterIMP;
import com.tiptech.android.ui.fragment.quiz.QuizView;
import com.tiptech.android.utils.rx.AppSchedulerProvider;
import com.tiptech.android.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ActivityModule { // providing (Adapters, Presenters, LayoutManagers, Disposables)
    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }

    @Provides
    BasePresenter<BaseView> provideBasePresenter(BasePresenterIMP<BaseView> presenter) {
        return presenter;
    }

    @Provides
    MainPresenter<MainView> providesMainPresenter(MainPresenterIMP<MainView> presenterIMP) {
        return presenterIMP;
    }

    @Provides
    SplashPresenter<SplashView> providesSplashPresenter(SplashPresenterIMP<SplashView> presenterIMP) {
        return presenterIMP;
    }

    @Provides
    HomePresenter<HomeView> providesHomePresenter(HomePresenterIMP<HomeView> presenterIMP) {
        return presenterIMP;
    }

    @Provides
    CoursesPresenter<CoursesView> providesCoursesPresenter(CoursesPresenterIMP<CoursesView> presenterIMP) {
        return presenterIMP;
    }

    @Provides
    NewsPresenter<NewsView> providesNewsPresenter(NewsPresenterIMP<NewsView> presenterIMP) {
        return presenterIMP;
    }

    @Provides
    QuizPresenter<QuizView> providesQuizPresenter(QuizPresenterIMP<QuizView> presenterIMP) {
        return presenterIMP;
    }
}
