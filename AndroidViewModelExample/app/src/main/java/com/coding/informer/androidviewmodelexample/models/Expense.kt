package com.coding.informer.androidviewmodelexample.models

import java.lang.IllegalArgumentException
import java.util.*

class Expense(
    val id: Long?,
    val title: String,
    val amount: Double,
    val date: Date,
    val checked: Boolean)
{
    constructor(
        title: String,
        amount: Double,
        date: Date,
        checked: Boolean): this(null, title, amount, date, checked)

    init {
        if(title.isEmpty()){
            throw IllegalArgumentException("Title is empty")
        }

    }

    fun isRevenue() = amount < 0
}