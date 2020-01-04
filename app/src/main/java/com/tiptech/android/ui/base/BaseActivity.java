package com.tiptech.android.ui.base;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.snackbar.Snackbar;
import com.tiptech.android.App;
import com.tiptech.android.R;
import com.tiptech.android.di.componant.ActivityComponent;
import com.tiptech.android.di.componant.DaggerActivityComponent;
import com.tiptech.android.di.module.ActivityModule;
import com.tiptech.android.utils.CommonUtils;
import com.tiptech.android.utils.LocaleManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.Unbinder;

public abstract class
BaseActivity extends AppCompatActivity implements BaseView {
    private static final String TAG = "BaseActivity";

    private ActivityComponent mActivityComponent;
    private Unbinder mUnBinder;
    private ProgressDialog mProgressDialog;

    @Inject
    BasePresenter<BaseView> mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((App) getApplication()).getComponent()) // for exposing needed instances from parent component
                .build();
        mActivityComponent.inject(this);
    }

    private void setAppLocale(String appLanguage) {
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }


    public void hideKeyboard() {
    }


    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    protected void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    @Override
    public void replaceFragment(Fragment fragment, int containerId) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerId, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void replaceFragment(Fragment fragment, int containerId, Bundle data) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragment.setArguments(data);
        fragmentTransaction.replace(containerId, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void pushFragment(Fragment fragment, int id) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(id, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void showFullLoading() {
        mProgressDialog = CommonUtils.showLoadingProgressDialog(this, "Loading ");
    }

    @Override
    public void hideFullLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void onError(int resId) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void showToastMessage(String message) {

    }

    @Override
    public void showToastMessage(@StringRes int resId) {
    }

    @Override
    public void showSnackMessageError(String message) {
        Snackbar snackbar = Snackbar.make(this.findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        sbView.setBackgroundColor(0xffff0004);
        snackbar.show();
    }

    @Override
    public void showSnackMessageSuccess(String message) {
        Snackbar snackbar = Snackbar.make(this.findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        sbView.setBackgroundColor(0xff00ff40);
        snackbar.show();
    }

    @Override
    public void showSnackMessageSuccess(int resId) {
        Snackbar snackbar = Snackbar.make(this.findViewById(android.R.id.content),
                resId, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        sbView.setBackgroundColor(0xff00ff40);
        snackbar.show();
    }

    @Override
    public void showSnackMessageError(int resId) {
        Snackbar snackbar = Snackbar.make(this.findViewById(android.R.id.content),
                resId, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        sbView.setBackgroundColor(0xffff0004);
        snackbar.show();
    }

    @Override
    public void showLoadingProgressDialog(String message) {

    }

    @Override
    public void hideLoadingProgressDialog() {

    }

    @Override
    public void getDate(String format, boolean withTime, BaseDateCallback callback) {

        final Calendar currentDate = Calendar.getInstance();
        Calendar date = Calendar.getInstance();
        new DatePickerDialog(this, R.style.DialogTheme, (DatePicker view, int year, int monthOfYear, int dayOfMonth) -> {
            date.set(year, monthOfYear, dayOfMonth);
            if (withTime)
                new TimePickerDialog(this, R.style.DialogTheme, (TimePicker view1, int hourOfDay, int minute) -> {
                    date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    date.set(Calendar.MINUTE, minute);
                    Log.v(TAG, "The choosen one " + date.getTime());
                    SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
                    String dateString = sdf.format(date.getTime());
                    callback.onDateSet(dateString);

                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
            else {
                SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
                String dateString = sdf.format(date.getTime());
                callback.onDateSet(dateString);
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();


    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void removeFragment(Fragment fragment) {
        FragmentManager manager = this.getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.remove(fragment);
        trans.commit();
        manager.popBackStack();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.setLocale(base));
    }


}
