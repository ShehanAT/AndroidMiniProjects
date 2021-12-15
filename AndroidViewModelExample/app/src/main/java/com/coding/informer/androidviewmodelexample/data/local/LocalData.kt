package com.coding.informer.androidviewmodelexample.data.local

import android.content.Context
import android.content.SharedPreferences
import com.coding.informer.androidviewmodelexample.FAVOURITES_KEY
import com.coding.informer.androidviewmodelexample.SHARED_PREFERENCE_FILE_NAME
import com.coding.informer.androidviewmodelexample.data.Resource
import com.coding.informer.androidviewmodelexample.data.error.PASSWORD_ERROR
import com.coding.informer.androidviewmodelexample.data.login.LoginRequest
import com.coding.informer.androidviewmodelexample.data.login.LoginResponse
import javax.inject.Inject


/**
 * Created by AhmedEltaher
 */

class LocalData @Inject constructor(val context: Context){

    fun doLogin(loginRequest: LoginRequest): Resource<LoginResponse> {
        if(loginRequest == LoginRequest("shehanatuk@gmail.com", "testing")){
            return Resource.Success(LoginResponse("1", "Shehan", "Atukorala",
            "Victoria Street", "555", "12032", "Ontario", "Canada", "shehanatuk@gmail.com"))

        }
        return Resource.DataError(PASSWORD_ERROR)
    }

    fun getCachedFavourites(): Resource<Set<String>> {
        val sharedPref = context.getSharedPreferences(SHARED_PREFERENCE_FILE_NAME, 0)
        return Resource.Success(sharedPref.getStringSet(FAVOURITES_KEY, setOf()) ?: setOf())
    }

    fun isFavourite(id: String): Resource<Boolean> {
        val sharedPref = context.getSharedPreferences(SHARED_PREFERENCE_FILE_NAME, 0)
        val cache = sharedPref.getStringSet(FAVOURITES_KEY, setOf<String>()) ?: setOf()
        return Resource.Success(cache.contains(id))
    }

    fun cacheFavourites(ids: Set<String>): Resource<Boolean> {
        val sharedPref = context.getSharedPreferences(SHARED_PREFERENCE_FILE_NAME, 0)
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putStringSet(FAVOURITES_KEY, ids)
        editor.apply()
        val isSuccess = editor.commit()
        return Resource.Success(isSuccess)
    }

    fun removeFromFavourites(id: String): Resource<Boolean> {
        val sharedPref = context.getSharedPreferences(SHARED_PREFERENCE_FILE_NAME, 0)
        var set = sharedPref.getStringSet(FAVOURITES_KEY, mutableSetOf<String>())?.toMutableSet() ?: mutableSetOf()
        if(set.contains(id)){
            set.remove(id)
        }
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.clear()
        editor.apply()
        editor.commit()
        editor.putStringSet(FAVOURITES_KEY, set)
        editor.apply()
        val isSuccess = editor.commit()
        return Resource.Success(isSuccess)
    }
}