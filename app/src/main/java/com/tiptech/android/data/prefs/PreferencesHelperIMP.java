

package com.tiptech.android.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.tiptech.android.di.ApplicationContext;
import com.tiptech.android.di.PreferenceInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PreferencesHelperIMP implements PreferencesHelper {
    private final SharedPreferences mPrefs;
    private final Context context;

    @Inject
    public PreferencesHelperIMP(@ApplicationContext Context context, @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
        this.context = context;
    }
}
