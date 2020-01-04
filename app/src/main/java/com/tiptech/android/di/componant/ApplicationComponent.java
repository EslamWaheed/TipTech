package com.tiptech.android.di.componant;

import android.app.Application;
import android.content.Context;

import com.tiptech.android.App;
import com.tiptech.android.data.DataManager;
import com.tiptech.android.data.prefs.PreferencesHelper;
import com.tiptech.android.di.ApplicationContext;
import com.tiptech.android.di.module.ApplicationModule;
import com.tiptech.android.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {

    // inject into
    void inject(App app);

    // exposing these instances to dependent components
    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();

    PreferencesHelper getPreferencesHelper();
}
