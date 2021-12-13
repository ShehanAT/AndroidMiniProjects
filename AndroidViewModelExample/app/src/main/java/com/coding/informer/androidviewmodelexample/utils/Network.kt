package com.coding.informer.androidviewmodelexample.utils

import android.content.Context
import android.net.NetworkInfo
import javax.inject.Inject
import android.net.ConnectivityManager

class Network @Inject constructor(val context: Context): NetworkConnectivity{
    override fun getNetworkInfo(): NetworkInfo? {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo
    }

    override fun isConnected(): Boolean {
        val info = getNetworkInfo()
        return info != null && info.isConnected
    }
}

interface NetworkConnectivity {
    fun getNetworkInfo(): NetworkInfo?
    fun isConnected(): Boolean
}