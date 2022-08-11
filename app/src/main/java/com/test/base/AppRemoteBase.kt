package com.test.base


import com.google.gson.GsonBuilder
import com.test.BuildConfig
import com.test.domain.remote.BaseRemoteApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppRemoteBase {
    companion object DataSourceProperties {
        //const val SERVER_URL = "https://desga.es/"
        const val BaseUrl = "https://api.openweathermap.org/data/"
        const val ApiId = "deaf27a0eee360a4d0e99bb5d2a3250f"
    }

    @Singleton
    @Provides
    fun provideService(): BaseRemoteApi {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .callbackExecutor(Executors.newSingleThreadExecutor())
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .client(createOkHttpClient())
        return retrofitBuilder
            .build()
            .create(BaseRemoteApi::class.java)
    }

    private fun createOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .connectTimeout(20L, TimeUnit.SECONDS)
            .readTimeout(20L, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor).build()
    }
}
