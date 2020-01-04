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
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;
import com.tiptech.android.R;
import com.tiptech.android.di.componant.ActivityComponent;
import com.tiptech.android.utils.CommonUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.Unbinder;

public class BaseBottomSheet extends BottomSheetDialogFragment implements BaseView {
    public static final String TAG = "BaseBottomSheet";
    private BaseActivity mActivity;
    private Unbinder mUnBinder;
    private ProgressDialog mProgressDialog;

    ProgressDialog mLoadingProgressDialog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    public ActivityComponent getActivityComponent() {
        if (mActivity != null) {
            return mActivity.getActivityComponent();
        }
        return null;
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }


    @Override
    public void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    @Override
    public void pushFragment(Fragment fragment, int id) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(id, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void showFullLoading() {
        hideFullLoading();
        mProgressDialog = CommonUtils.showLoadingProgressDialog(this.getContext(), "Loading...");
    }

    @Override
    public void hideFullLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void onError(int resId) {
        if (mActivity != null) {
            mActivity.onError(resId);
        }
    }

    @Override
    public void onError(String message) {
        if (mActivity != null) {
            mActivity.onError(message);
        }
    }

    @Override
    public void showToastMessage(String message) {
        if (mActivity != null) {
            mActivity.showToastMessage(message);
        }
    }

    @Override
    public void showToastMessage(int resId) {
        if (mActivity != null) {
            mActivity.showToastMessage(resId);
        }
    }

    @Override
    public void showSnackMessageError(String message) {
        Snackbar snackbar = Snackbar.make(getBaseActivity().findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(getBaseActivity(), R.color.colorPrimary));
        snackbar.show();
    }

    @Override
    public void showSnackMessageError(int resId) {
        Snackbar snackbar = Snackbar.make(getBaseActivity().findViewById(android.R.id.content),
                resId, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(getBaseActivity(), R.color.colorPrimary));
        snackbar.show();
    }

    @Override
    public void showSnackMessageSuccess(String message) {
        Snackbar snackbar = Snackbar.make(getBaseActivity().findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(getBaseActivity(), R.color.white));
        sbView.setBackgroundColor(0xff00ff40);
        snackbar.show();
    }

    @Override
    public void showSnackMessageSuccess(int resId) {
        Snackbar snackbar = Snackbar.make(getBaseActivity().findViewById(android.R.id.content),
                resId, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(getBaseActivity(), R.color.white));
        sbView.setBackgroundColor(0xff00ff40);
        snackbar.show();
    }

    @Override
    public void showLoadingProgressDialog(String message) {
        hideLoadingProgressDialog();
        mLoadingProgressDialog = CommonUtils.showLoadingProgressDialog(this.getContext(), message);
    }

    @Override
    public void hideLoadingProgressDialog() {
        if (mLoadingProgressDialog != null) {
            mLoadingProgressDialog.dismiss();
        }
    }

    @Override
    public boolean isNetworkConnected() {
        if (mActivity != null) {
            return mActivity.isNetworkConnected();
        }
        return false;
    }

    @Override
    public void hideKeyboard() {
        if (mActivity != null) {
            mActivity.hideKeyboard();
        }
    }

    @Override
    public void replaceFragment(Fragment fragment, int containerId) {

    }

    @Override
    public void replaceFragment(Fragment fragment, int containerId ,Bundle data) {

    }

    @Override
    public void getDate(String format, boolean withTime, BaseDateCallback callback) {

        final Calendar currentDate = Calendar.getInstance();
        Calendar date = Calendar.getInstance();
        new DatePickerDialog(getContext(), R.style.DialogTheme, (DatePicker view, int year, int monthOfYear, int dayOfMonth) -> {
            date.set(year, monthOfYear, dayOfMonth);
            if (withTime)
                new TimePickerDialog(getContext(), R.style.DialogTheme, (TimePicker view1, int hourOfDay, int minute) -> {
                    date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    date.set(Calendar.MINUTE, minute);
                    Log.v(TAG, "The choosen one " + date.getTime());
                    SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
                    String dateString = sdf.format(date.getTime());
                    callback.onDateSet(dateString);

                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
            else{
                SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
                String dateString = sdf.format(date.getTime());
                callback.onDateSet(dateString);
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();


    }

    @Override
    public void removeFragment(Fragment fragment) {

    }

    public interface Callback {

        void onFragmentAttached(String tag);

        void pushFragment(Fragment fragment);

        void onFragmentDetached(String tag);
    }

}
