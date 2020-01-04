package com.tiptech.android.ui.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tiptech.android.R;
import com.tiptech.android.di.componant.ActivityComponent;
import com.tiptech.android.model.courses.CoursesResponse;
import com.tiptech.android.model.news.NewsResponse;
import com.tiptech.android.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment implements HomeView {
    private static final String TAG = "HomeFragment";
    @Inject
    HomePresenter<HomeView> mPresenter;
    View view;

    @BindView(R.id.recyclerView_home_courses_list)
    RecyclerView recyclerView_home_courses_list;
    @BindView(R.id.recyclerView_home_news_list)
    RecyclerView recyclerView_home_news_list;

    CoursesAdapter coursesAdapter;
    NewsAdapter newsAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_home, container, false);
            initInjections(view);
            initViews(view);
        } else {
            initInjections(view);
        }
        return view;
    }

    private void initViews(final View view) {
        mPresenter.getCourses();
        coursesAdapter = new CoursesAdapter(mPresenter, getContext());
        recyclerView_home_courses_list.setAdapter(coursesAdapter);

        mPresenter.getNews();
        newsAdapter = new NewsAdapter(mPresenter, getContext());
        recyclerView_home_news_list.setAdapter(newsAdapter);
    }

    private void initInjections(View view) {
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }
    }

    @Override
    public void showCourses(CoursesResponse coursesResponse) {
        coursesAdapter.refreshList(coursesResponse.getData());
    }

    @Override
    public void showCoursesError(String error) {
        showSnackMessageError(error);
    }

    @Override
    public void showNews(NewsResponse newsResponse) {
        newsAdapter.refreshList(newsResponse.getData());
    }

    @Override
    public void showNewsError(String error) {
        showSnackMessageError(error);
    }
}
