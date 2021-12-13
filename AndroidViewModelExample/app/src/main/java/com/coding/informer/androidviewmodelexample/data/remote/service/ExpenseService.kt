package com.coding.informer.androidviewmodelexample.data.remote.service

import com.coding.informer.androidviewmodelexample.data.dto.expenses.ExpensesItem
import retrofit2.Response
import retrofit2.http.GET

interface ExpenseService {
    @GET("expenses.json")
    suspend fun fetchExpenses(): Response<List<ExpensesItem>>
}