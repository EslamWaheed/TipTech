package com.tiptech.android.utils;

import android.graphics.Color;
import android.text.TextUtils;
import android.util.Patterns;

public final class AppConstants {
    static final int LOADING_BOUNCE_COLOR = Color.WHITE;
    private static final String TAG = "AppConstants";
    public static final String PREF_NAME = "_preferences";
    public static final String API_BASE_URL = "http://zeinabmostafa-001-site1.etempurl.com/";
    public static final String API_BASE_URL_IMAGE = "http://zeinabmostafa-001-site1.etempurl.com/Common/PreviewImage/?dbField=";

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    private AppConstants() {
    }

}
