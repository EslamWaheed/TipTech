package com.tiptech.android.ui.fragment.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tiptech.android.R;
import com.tiptech.android.di.module.GlideApp;
import com.tiptech.android.model.news.Datum;
import com.tiptech.android.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private static final String TAG = "NewsAdapter";
    RecyclerView mRecyclerView;
    HomePresenter<HomeView> mPresenter;
    Context context;
    List<Datum> dataList;


    public NewsAdapter(HomePresenter<HomeView> mPresenter, Context context) {
        this.mPresenter = mPresenter;
        this.context = context;
        dataList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_news, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Datum datum = dataList.get(i);
        GlideApp.with(context)
                .load(AppConstants.API_BASE_URL_IMAGE + datum.getPhoto())
                .into(viewHolder.imageView_news_image);
        viewHolder.textView_news_title.setText(datum.getTitle());
        viewHolder.textView_news_description.setText(datum.getDescription());
        viewHolder.textView_news_date.setText(datum.getDateNew());
    }

    @Override
    public int getItemCount() {
        if (dataList != null) {
            return dataList.size();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View view;

        @BindView(R.id.imageView_news_image)
        ImageView imageView_news_image;
        @BindView(R.id.textView_news_title)
        TextView textView_news_title;
        @BindView(R.id.textView_news_description)
        TextView textView_news_description;
        @BindView(R.id.textView_news_date)
        TextView textView_news_date;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
        }

    }

    void refreshList(List<Datum> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    void clearList() {
        this.dataList.clear();
        notifyDataSetChanged();
    }
}
