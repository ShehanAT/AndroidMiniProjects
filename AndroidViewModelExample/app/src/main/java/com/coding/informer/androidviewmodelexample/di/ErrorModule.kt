package com.coding.informer.androidviewmodelexample.di

import com.coding.informer.androidviewmodelexample.data.error.mapper.ErrorMapperSource
import com.coding.informer.androidviewmodelexample.usecase.errors.ErrorManager
import com.coding.informer.androidviewmodelexample.usecase.errors.ErrorUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// With @Module we are telling Dagger that this is a Dagger module
//@Module
//@InstallIn(SingletonComponent::class)
//abstract class ErrorModule {
//    @Binds
//    @Singleton
//    abstract fun provideErrorFactoryImpl(errorManager: ErrorManager): ErrorUseCase

//    @Binds
//    @Singleton
//    abstract fun provideErrorMapper(errorManager: ErrorManager): ErrorMapperSource
//}