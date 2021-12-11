package com.coding.informer.androidloginregistrationexample.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class SessionManager {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private String KEY_IS_LOGGEDIN = "isLoggedIn";
    private String PREF_NAME = "AndroidLogin";
    private int PRIVATE_MODE = 0;

    public SessionManager(Context context){
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();

    }

    public void setLogin(boolean isLoggedIn){
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.commit();
        Log.d(TAG, "User login session modified");
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

}
