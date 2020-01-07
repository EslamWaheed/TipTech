package com.tiptech.android.ui.fragment.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tiptech.android.R;
import com.tiptech.android.di.module.GlideApp;
import com.tiptech.android.model.courses.Datum;
import com.tiptech.android.utils.AppConstants;
import com.tiptech.android.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.ViewHolder> {
    private static final String TAG = "CoursesAdapter";
    RecyclerView mRecyclerView;
    HomePresenter<HomeView> mPresenter;
    Context context;
    List<Datum> dataList;


    public CoursesAdapter(HomePresenter<HomeView> mPresenter, Context context) {
        this.mPresenter = mPresenter;
        this.context = context;
        dataList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_courses, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Datum datum = dataList.get(i);
        GlideApp.with(context)
                .load(AppConstants.API_BASE_URL_IMAGE + datum.getPhoto())
                .into(viewHolder.imageView_courses_image);
        viewHolder.textView_courses_title.setText(datum.getName());
        viewHolder.textView_courses_description.setText(datum.getDescription());
        viewHolder.ratingBar_courses_rating.setRating(datum.getEvaluation());
        viewHolder.textView_courses_source.setText(datum.getNameSite());
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (datum.getLink() != null && !datum.getLink().equals(""))
                    CommonUtils.openCustomTab(context, datum.getLink());
            }
        });
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

        @BindView(R.id.imageView_courses_image)
        ImageView imageView_courses_image;
        @BindView(R.id.textView_courses_title)
        TextView textView_courses_title;
        @BindView(R.id.textView_courses_description)
        TextView textView_courses_description;
        @BindView(R.id.ratingBar_courses_rating)
        RatingBar ratingBar_courses_rating;
        @BindView(R.id.textView_courses_source)
        TextView textView_courses_source;

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
