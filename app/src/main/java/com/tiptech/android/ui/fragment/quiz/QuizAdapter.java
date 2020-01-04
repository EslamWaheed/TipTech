package com.tiptech.android.ui.fragment.quiz;

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
import com.tiptech.android.model.quiz.Datum;
import com.tiptech.android.utils.AppConstants;
import com.tiptech.android.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder> {
    private static final String TAG = "CoursesAdapter";
    RecyclerView mRecyclerView;
    QuizPresenter<QuizView> mPresenter;
    Context context;
    List<Datum> dataList;


    public QuizAdapter(QuizPresenter<QuizView> mPresenter, Context context) {
        this.mPresenter = mPresenter;
        this.context = context;
        dataList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_quiz, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Datum datum = dataList.get(i);
        GlideApp.with(context)
                .load(AppConstants.API_BASE_URL_IMAGE + datum.getPhoto())
                .into(viewHolder.imageView_quiz_image);
        viewHolder.textView_quiz_name.setText(datum.getText());
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonUtils.openCustomTab(context, datum.getDescription());
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

        @BindView(R.id.imageView_quiz_image)
        ImageView imageView_quiz_image;
        @BindView(R.id.textView_quiz_name)
        TextView textView_quiz_name;

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