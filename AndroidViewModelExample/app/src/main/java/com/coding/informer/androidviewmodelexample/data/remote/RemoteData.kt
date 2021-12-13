package com.coding.informer.androidviewmodelexample.data.remote

import com.coding.informer.androidviewmodelexample.data.Resource
import com.coding.informer.androidviewmodelexample.data.dto.expenses.Expenses
import com.coding.informer.androidviewmodelexample.data.dto.expenses.ExpensesItem
import com.coding.informer.androidviewmodelexample.data.error.NETWORK_ERROR
import com.coding.informer.androidviewmodelexample.data.error.NO_INTERNET_CONNECTION
import com.coding.informer.androidviewmodelexample.data.remote.service.ExpenseService
import com.coding.informer.androidviewmodelexample.data.remote.service.ServiceGenerator
import com.coding.informer.androidviewmodelexample.models.Expense
import com.coding.informer.androidviewmodelexample.utils.NetworkConnectivity
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class RemoteData @Inject constructor(private val serviceGenerator: ServiceGenerator, private val networkConnectivity: NetworkConnectivity): RemoteDataSource {
    override suspend fun requestExpenses(): Resource<Expenses> {
        val expenseService = serviceGenerator.createService(ExpenseService::class.java)
        return when (val response = processCall(expenseService::fetchExpenses)){
            is List<*> -> {
                Resource.Success(data=Expenses(response as ArrayList<ExpensesItem>))
            }
            else -> {
                Resource.DataError(errorCode=response as Int)
            }
        }
    }

    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if(!networkConnectivity.isConnected()){
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if(response.isSuccessful){
                response.body()
            }else{
                responseCode
            }
        }catch(e: IOException){
            NETWORK_ERROR
        }
    }
}