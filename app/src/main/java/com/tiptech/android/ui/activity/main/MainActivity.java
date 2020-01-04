package com.tiptech.android.ui.activity.main;


import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tiptech.android.R;
import com.tiptech.android.ui.base.BaseActivity;
import com.tiptech.android.ui.fragment.courses.CoursesFragment;
import com.tiptech.android.ui.fragment.home.HomeFragment;
import com.tiptech.android.ui.fragment.news.NewsFragment;
import com.tiptech.android.ui.fragment.quiz.QuizFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView {
    private static final String TAG = "MainActivity";
    @Inject
    MainPresenter<MainView> mPresenter;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottom_navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);
        pushFragment(new HomeFragment(), R.id.container);
        bottom_navigation.setOnNavigationItemSelectedListener(menuItem -> {
            mPresenter.onNavigationClick(menuItem.getItemId());
            return true;
        });
    }

    @Override
    public void changeNavigationFragment(int id) {
        switch (id) {
            case R.id.navigation_home:
                Log.d(TAG, "navigation_home");
                replaceFragment(new HomeFragment(), R.id.container);
                break;

            case R.id.navigation_courses:
                Log.d(TAG, "navigation_courses");
                replaceFragment(new CoursesFragment(), R.id.container);
                break;

            case R.id.navigation_news:
                Log.d(TAG, "navigation_news");
                replaceFragment(new NewsFragment(), R.id.container);
                break;

            case R.id.navigation_quiz:
                Log.d(TAG, "navigation_quiz");
                replaceFragment(new QuizFragment(), R.id.container);
                break;
        }
    }
}
