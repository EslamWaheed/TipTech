package com.tiptech.android.ui.base;

public interface BaseRequestCallBack<T> {
    void onSuccess(T response) ;
    void error(String error);

}
