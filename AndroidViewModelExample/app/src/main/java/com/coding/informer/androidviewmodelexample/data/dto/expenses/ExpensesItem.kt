package com.coding.informer.androidviewmodelexample.data.dto.expenses

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import java.util.*


/**
 * Data classes are classes that are primarily designed to hold data
 */
@JsonClass(generateAdapter=false)
@Parcelize
data class ExpensesItem(
    @Json(name="expense_id")
    val expenseId: Int = 0,
    @Json(name="title")
    val title: String = "",
    @Json(name="description")
    val description: String = "",
    @Json(name="amount")
    val amount: Double = 0.toDouble(),
    @Json(name="date")
    val date: Date
): Parcelable {


}