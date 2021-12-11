package com.coding.informer.androidviewmodelexample.data

import com.coding.informer.androidviewmodelexample.data.local.LocalData
import com.coding.informer.androidviewmodelexample.data.login.LoginRequest
import com.coding.informer.androidviewmodelexample.data.login.LoginResponse
import com.coding.informer.androidviewmodelexample.models.Expense
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


/**
 * Created by AhmedEltaher
 */
class DataRepository @Inject constructor(private val remoteRepository: RemoteData, private val localRepository: LocalData, private val ioDispatcher: CoroutineContext): DataRepositorySource {
    override suspend fun requestExpenses(): Flow<Resource<Expense>> {
        TODO("Not yet implemented")
    }

    override suspend fun doLogin(loginRequest: LoginRequest): Flow<Resource<LoginResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun addToFavourite(id: String): Flow<Resource<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun removeFromFavourite(id: String): Flow<Resource<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun isFavourite(id: String): Flow<Resource<Boolean>> {
        TODO("Not yet implemented")
    }
}