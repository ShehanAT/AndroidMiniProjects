package com.coding.informer.androidviewmodelexample.di

import com.coding.informer.androidviewmodelexample.data.DataRepository
import com.coding.informer.androidviewmodelexample.data.DataRepositorySource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


// Tells Dagger that this is a Dagger module
@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideDataRepository(dataRepository: DataRepository): DataRepositorySource
}