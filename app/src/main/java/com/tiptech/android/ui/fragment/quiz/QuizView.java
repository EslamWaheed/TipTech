package com.tiptech.android.ui.fragment.quiz;

import com.tiptech.android.model.quiz.QuizResponse;
import com.tiptech.android.ui.base.BaseView;

public interface QuizView extends BaseView {
    void showQuiz(QuizResponse quizResponse);

    void showQuizError(String error);
}
