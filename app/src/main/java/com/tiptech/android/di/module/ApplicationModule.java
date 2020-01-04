package com.tiptech.android.di.module;

import android.app.Application;
import android.content.Context;


import com.tiptech.android.R;
import com.tiptech.android.data.DataManager;
import com.tiptech.android.data.DataManagerIMP;
import com.tiptech.android.data.prefs.PreferencesHelper;
import com.tiptech.android.data.prefs.PreferencesHelperIMP;
import com.tiptech.android.di.ApplicationContext;
import com.tiptech.android.di.PreferenceInfo;
import com.tiptech.android.utils.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(DataManagerIMP dataManagerIMP) {
        return dataManagerIMP;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName(@ApplicationContext Context context) {
        return context.getString(R.string.app_name) + AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(PreferencesHelperIMP preferencesHelperIMP) {
        return preferencesHelperIMP;
    }
}