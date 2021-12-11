package com.coding.informer.androidviewmodelexample.data.local

import android.content.Context
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
}