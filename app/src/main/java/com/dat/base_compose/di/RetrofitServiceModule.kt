package com.dat.base_compose.di

import android.app.Application
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.dat.base_compose.BuildConfig
import com.dat.base_compose.common.Constants
import com.dat.base_compose.data.remote.service.DemoService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitServiceModule {
    private fun getHttpClient(
        application: Application,
    ): OkHttpClient {
        return OkHttpClient.Builder().also { client ->
            client.retryOnConnectionFailure(true)
            if (BuildConfig.DEBUG) {
                val loggingContent = HttpLoggingInterceptor()
                loggingContent.setLevel(HttpLoggingInterceptor.Level.BODY)
                val collector = ChuckerCollector(application)
                val logging = ChuckerInterceptor.Builder(application).alwaysReadResponseBody(true)
                    .collector(collector).build()
                client.interceptors().add(logging)
                client.interceptors().add(loggingContent)
            }
            client.connectTimeout(120, TimeUnit.SECONDS)
            client.readTimeout(120, TimeUnit.SECONDS)
            client.protocols(Collections.singletonList(Protocol.HTTP_1_1))
        }.build()
    }

    @Provides
    @Singleton
    @Named(Constants.Inject.API)
    fun provideRetrofit(
        gson: Gson,
        application: Application,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(getHttpClient(application))
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideService(@Named(Constants.Inject.API) retrofit: Retrofit): DemoService =
        retrofit.create(DemoService::class.java)

}


