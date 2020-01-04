package com.tiptech.android.ui.base;

public interface BasePresenter<V extends BaseView> {

    void onAttach(V view);

    void onDetach();

}
