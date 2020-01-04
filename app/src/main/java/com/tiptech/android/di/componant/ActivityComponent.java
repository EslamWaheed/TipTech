package com.tiptech.android.di.componant;

import com.tiptech.android.di.PerActivity;
import com.tiptech.android.di.module.ActivityModule;
import com.tiptech.android.ui.activity.main.MainActivity;
import com.tiptech.android.ui.activity.splash.SplashActivity;
import com.tiptech.android.ui.base.BaseActivity;
import com.tiptech.android.ui.fragment.courses.CoursesFragment;
import com.tiptech.android.ui.fragment.home.HomeFragment;
import com.tiptech.android.ui.fragment.news.NewsFragment;
import com.tiptech.android.ui.fragment.quiz.QuizFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(BaseActivity baseActivity);

    void inject(MainActivity mainActivity);

    void inject(SplashActivity splashActivity);

    void inject(HomeFragment homeFragment);

    void inject(CoursesFragment coursesFragment);

    void inject(NewsFragment newsFragment);

    void inject(QuizFragment quizFragment);
}
