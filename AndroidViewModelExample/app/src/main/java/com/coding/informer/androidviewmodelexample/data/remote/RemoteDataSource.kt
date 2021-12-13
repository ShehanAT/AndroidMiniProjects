package com.coding.informer.androidviewmodelexample.data.remote

import com.coding.informer.androidviewmodelexample.data.Resource
import com.coding.informer.androidviewmodelexample.data.dto.expenses.Expenses

internal interface RemoteDataSource {
    suspend fun requestExpenses(): Resource<Expenses>
}