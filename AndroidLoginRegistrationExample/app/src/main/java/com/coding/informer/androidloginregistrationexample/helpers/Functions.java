package com.coding.informer.androidloginregistrationexample.helpers;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.coding.informer.androidloginregistrationexample.widget.ProgressBarDialog;

public class Functions {
    public static DialogFragment showProgressDialog(Context context, String title){
        FragmentManager fm = ((AppCompatActivity)context).getSupportFragmentManager();
        DialogFragment newFragment = ProgressBarDialog.newInstance(title);
        newFragment.show(fm, "dialog");
        return newFragment;
    }
}
