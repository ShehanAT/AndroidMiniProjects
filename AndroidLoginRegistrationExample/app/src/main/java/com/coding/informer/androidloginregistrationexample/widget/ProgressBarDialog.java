package com.coding.informer.androidloginregistrationexample.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.coding.informer.androidloginregistrationexample.R;

public class ProgressBarDialog extends DialogFragment {
    private Bundle bundle;

    public ProgressBarDialog(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
    }

    public static ProgressBarDialog newInstance(String title){
        ProgressBarDialog myFragment = new ProgressBarDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        myFragment.setArguments(args);
        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.dialog_progress, container, false);
    }
}
