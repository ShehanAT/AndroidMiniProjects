package com.coding.informer.androidviewmodelexample.data.error.mapper

import android.content.Context
import com.coding.informer.androidviewmodelexample.R
import com.coding.informer.androidviewmodelexample.data.error.*
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ErrorMapper @Inject constructor(@ApplicationContext val context: Context): ErrorMapperSource {


    override fun getErrorString(errorId: Int): String {
        return context.getString(errorId)
    }

    override val errorsMap: Map<Int, String>
        get() = mapOf(
            Pair(NO_INTERNET_CONNECTION, getErrorString(R.string.no_internet)),
            Pair(NETWORK_ERROR, getErrorString(R.string.network_error)),
            Pair(PASSWORD_ERROR, getErrorString(R.string.invalid_password)),
            Pair(USERNAME_ERROR, getErrorString(R.string.invalid_username)),
            Pair(INVALID_USERNAME_AND_PASSWORD, getErrorString(R.string.invalid_username_and_password)),
            Pair(SEARCH_ERROR, getErrorString(R.string.search_error))
        ).withDefault { getErrorString(R.string.network_error)}
}