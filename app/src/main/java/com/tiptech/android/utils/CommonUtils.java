package com.tiptech.android.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.browser.customtabs.CustomTabsIntent;

import com.tiptech.android.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;


public final class CommonUtils {

    private static final String TAG = "CommonUtils";

    private CommonUtils() {
        // This utility class is not publicly instantiable
    }

    public static String inputStreamToString(InputStream inputStream) {
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, bytes.length);
            String json = new String(bytes);
            return json;
        } catch (IOException e) {
            return null;
        }
    }

    public static boolean isContainDigits(String name) {
        return name.matches(".*\\d+.*");
    }

    public static String getDeviceLanguage() {
        if (Locale.getDefault().getDisplayLanguage().equalsIgnoreCase("العربية")) {
            return "ar";
        }
        return "en";
    }

    public static ProgressDialog showLoadingProgressDialog(Context context, String message) {

        ProgressDialog dialog = new ProgressDialog(context); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(message);
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        return dialog;
    }

    public static String getPath(Uri uri, Context context) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        assert cursor != null;
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(projection[0]);
        String filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }


    public static void openCustomTab(Context context, String url) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        builder.setToolbarColor(context.getResources().getColor(R.color.colorPrimaryDark));
        builder.setActionButton(null, null, null, false);
        customTabsIntent.launchUrl(context, Uri.parse(url));
    }

}
