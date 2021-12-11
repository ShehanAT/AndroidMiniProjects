package com.coding.informer.androidviewmodelexample.views.base

import androidx.lifecycle.ViewModel
import com.coding.informer.androidviewmodelexample.usecase.errors.ErrorManager
import javax.inject.Inject

/**
 * Created by AhmedEltaher
 */
abstract class BaseViewModel: ViewModel() {
    /**
     * Inject Singleton ErrorManager
     * Use this errorManager to get the Errors
     */
    @Inject
    lateinit var errorManager: ErrorManager
}