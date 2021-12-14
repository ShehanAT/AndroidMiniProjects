package com.coding.informer.androidviewmodelexample.views.base.listeners

import com.coding.informer.androidviewmodelexample.data.dto.expenses.ExpensesItem

interface RecyclerItemListener {
    fun onItemSelected(expense: ExpensesItem)
}