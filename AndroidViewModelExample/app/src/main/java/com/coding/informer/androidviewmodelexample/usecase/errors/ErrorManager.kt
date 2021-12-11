package com.coding.informer.androidviewmodelexample.usecase.errors

import com.coding.informer.androidviewmodelexample.data.error.mapper.ErrorMapper
import javax.inject.Inject
import com.coding.informer.androidviewmodelexample.data.error.Error
/**
 * Created by AhmedEltaher
 */

class ErrorManager @Inject constructor(private val errorMapper: ErrorMapper): ErrorUseCase {
    override fun getError(errorCode: Int): Error {
        return Error(code=errorCode, description=errorMapper.errorsMap.getValue(errorCode))
    }
}