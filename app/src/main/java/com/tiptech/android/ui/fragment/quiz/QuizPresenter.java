package com.tiptech.android.ui.fragment.quiz;

import com.tiptech.android.ui.base.BasePresenter;

public interface QuizPresenter<V extends QuizView> extends BasePresenter<V> {
    void getQuiz();
}
