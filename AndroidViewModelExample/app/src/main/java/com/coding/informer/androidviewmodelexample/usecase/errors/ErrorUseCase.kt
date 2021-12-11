package com.coding.informer.androidviewmodelexample.usecase.errors

import com.coding.informer.androidviewmodelexample.data.error.Error

interface ErrorUseCase {
    fun getError(errorCode: Int): Error
}