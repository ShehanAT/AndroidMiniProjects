package com.coding.informer.androidviewmodelexample.data.remote.service

import androidx.viewbinding.BuildConfig
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton
import com.coding.informer.androidviewmodelexample.BASE_URL
import com.coding.informer.androidviewmodelexample.data.remote.moshiFactories.MyKotlinJsonAdapterFactory
import com.coding.informer.androidviewmodelexample.data.remote.moshiFactories.MyStandardJsonAdapters


private const val timeoutRead = 30
private const val contentType = "Content-Type"
private const val contentTypeValue = "application/json"
private const val timeoutCounter = 30

@Singleton
class ServiceGenerator @Inject constructor(){
    private val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    private val retrofit: Retrofit

    private val headerInterceptor = Interceptor { chain ->
        val original = chain.request()

        val request = original.newBuilder()
            .header(contentType, contentTypeValue)
            .method(original.method, original.body)
            .build()

        chain.proceed(request)
    }

    private val logger: HttpLoggingInterceptor
        get() {
            val loggingInterceptor = HttpLoggingInterceptor()
            if(BuildConfig.DEBUG){
                loggingInterceptor.apply { level = HttpLoggingInterceptor.Level.BODY }
            }
            return loggingInterceptor
        }

    init {
        okHttpBuilder.addInterceptor(headerInterceptor)
        okHttpBuilder.addInterceptor(logger)
        okHttpBuilder.connectTimeout(timeoutCounter.toLong(), TimeUnit.SECONDS)
        okHttpBuilder.readTimeout(timeoutRead.toLong(), TimeUnit.SECONDS)
        val client = okHttpBuilder.build()
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL).client(client)
            .addConverterFactory(MoshiConverterFactory.create(getMoshi()))
            .build()
    }

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }

    private fun getMoshi(): Moshi {
        return Moshi.Builder()
            .add(MyKotlinJsonAdapterFactory())
            .add(MyStandardJsonAdapters.FACTORY)
            .build()
    }
}