package com.coding.informer.androidviewmodelexample.utils

import android.icu.number.Precision.increment


/**
 * Contains a static reference to [IdlingResource], only availabe in the 'mock' build type
 */
class EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL"

    @JvmField
    val countingIdlingResource = SimpleCountingIdlingResource(RESOURCE)

    fun increment(){
        countingIdlingResource.increment()
    }

    fun decrement(){
        countingIdlingResource.decrement()
    }
}

inline fun <T> wrapExpressoIdlingResource(function: () -> T): T {
    // Expresso does not work well with coroutines yet. See
    // https://github.com/Kotlin/kotlinx.coroutines/issues/982
    EspressoIdlingResource.increment() // Set app as busy
    return try {
        function()
    } finally {
        EspressoIdlingResource.decrement() // Set app as idle
    }
}