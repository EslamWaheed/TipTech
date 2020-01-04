package com.tiptech.android.ui.base;


import android.os.Bundle;

import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

public interface BaseView {

    void pushFragment(Fragment fragment, int id);

    void showFullLoading();

    void hideFullLoading();

    void onError(@StringRes int resId);

    void onError(String message);

    void showToastMessage(String message);

    void showToastMessage(@StringRes int resId);

    void showSnackMessageError(String message);

    void showSnackMessageError(@StringRes int resId);

    void showSnackMessageSuccess(String message);

    void showSnackMessageSuccess(@StringRes int resId);

    void showLoadingProgressDialog(String message);

    void hideLoadingProgressDialog();

    boolean isNetworkConnected();

    void hideKeyboard();

    void replaceFragment(Fragment fragment, int containerId);

    void replaceFragment(Fragment fragment, int containerId , Bundle data);

    void getDate(String format , boolean withTime , BaseDateCallback callback);

    void removeFragment(Fragment fragment);


}
