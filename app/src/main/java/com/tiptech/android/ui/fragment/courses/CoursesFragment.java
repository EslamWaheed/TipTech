package com.tiptech.android.ui.fragment.courses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tiptech.android.R;
import com.tiptech.android.di.componant.ActivityComponent;
import com.tiptech.android.model.courses.CoursesResponse;
import com.tiptech.android.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CoursesFragment extends BaseFragment implements CoursesView {
    private static final String TAG = "CoursesFragment";
    @Inject
    CoursesPresenter<CoursesView> mPresenter;
    View view;

    @BindView(R.id.recyclerView_fragment_courses_list)
    RecyclerView recyclerView_fragment_courses_list;
    @BindView(R.id.textView_toolbar_title)
    TextView textView_toolbar_title;

    CoursesAdapter coursesAdapter;

    @OnClick(R.id.imageView_toolbar_back)
    void close(View view) {
        try {
            removeFragment(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_courses, container, false);
            initInjections(view);
            initViews(view);
        } else {
            initInjections(view);
        }
        return view;
    }

    private void initViews(final View view) {
        textView_toolbar_title.setText(getResources().getString(R.string.courses));
        mPresenter.getCourses();
        coursesAdapter = new CoursesAdapter(mPresenter, getContext());
        recyclerView_fragment_courses_list.setAdapter(coursesAdapter);
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
}
