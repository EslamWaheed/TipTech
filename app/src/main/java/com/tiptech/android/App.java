package com.tiptech.android;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import androidx.annotation.NonNull;

import com.tiptech.android.data.DataManager;
import com.tiptech.android.di.componant.ApplicationComponent;
import com.tiptech.android.di.componant.DaggerApplicationComponent;
import com.tiptech.android.di.module.ApplicationModule;
import com.tiptech.android.di.module.NetworkModule;
import com.tiptech.android.utils.AppConstants;
import com.tiptech.android.utils.LocaleManager;

import javax.inject.Inject;

public class App extends Application {
    private static final String TAG = "App";

    @Inject
    DataManager mDataManager;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule(AppConstants.API_BASE_URL))
                .build();
        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.setLocale(base));
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleManager.setLocale(this);
    }
}
