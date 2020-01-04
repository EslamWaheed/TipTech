package com.tiptech.android.ui.fragment.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tiptech.android.R;
import com.tiptech.android.di.componant.ActivityComponent;
import com.tiptech.android.model.news.NewsResponse;
import com.tiptech.android.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsFragment extends BaseFragment implements NewsView {
    private static final String TAG = "NewsFragment";
    @Inject
    NewsPresenter<NewsView> mPresenter;
    View view;

    @BindView(R.id.recyclerView_fragment_news_list)
    RecyclerView recyclerView_fragment_news_list;
    @BindView(R.id.textView_toolbar_title)
    TextView textView_toolbar_title;

    NewsAdapter newsAdapter;

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
            view = inflater.inflate(R.layout.fragment_news, container, false);
            initInjections(view);
            initViews(view);
        } else {
            initInjections(view);
        }
        return view;
    }

    private void initViews(final View view) {
        textView_toolbar_title.setText(getResources().getString(R.string.news));
        mPresenter.getNews();
        newsAdapter = new NewsAdapter(mPresenter, getContext());
        recyclerView_fragment_news_list.setAdapter(newsAdapter);
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
    public void showNews(NewsResponse newsResponse) {
        newsAdapter.refreshList(newsResponse.getData());
    }

    @Override
    public void showNewsError(String error) {
        showSnackMessageError(error);
    }
}
