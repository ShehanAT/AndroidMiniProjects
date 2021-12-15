package com.coding.informer.androidviewmodelexample.data

import com.coding.informer.androidviewmodelexample.data.dto.expenses.Expenses
import com.coding.informer.androidviewmodelexample.data.login.LoginRequest
import com.coding.informer.androidviewmodelexample.data.login.LoginResponse
import com.coding.informer.androidviewmodelexample.models.Expense
import kotlinx.coroutines.flow.Flow

interface DataRepositorySource {
    suspend fun requestExpenses(): Flow<Resource<Expenses>>
    suspend fun doLogin(loginRequest: LoginRequest): Flow<Resource<LoginResponse>>
    suspend fun addToFavourite(id: String): Flow<Resource<Boolean>>
    suspend fun removeFromFavourite(id: String): Flow<Resource<Boolean>>
    suspend fun isFavourite(id: String): Flow<Resource<Boolean>>


}