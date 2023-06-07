package com.dat.base_compose.core.di

import android.content.Context
import com.dat.base_compose.BuildConfig
import com.dat.base_compose.data.network.DemoServiceRetrofit
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitServiceModule {
    @Provides
    fun providesHttpClient(
        @ApplicationContext application: Context,
    ): Call.Factory {
        return OkHttpClient.Builder().also { client ->
            client.retryOnConnectionFailure(true)
            if (BuildConfig.DEBUG) {
                val loggingContent = HttpLoggingInterceptor()
                loggingContent.setLevel(HttpLoggingInterceptor.Level.BODY)
                val collector = ChuckerCollector(application)
                val logging = ChuckerInterceptor.Builder(application)
                    .alwaysReadResponseBody(true)
                    .collector(collector)
                    .build()
                client.addInterceptor(logging)
                client.addInterceptor(loggingContent)
            }
            client.connectTimeout(30, TimeUnit.SECONDS)
            client.readTimeout(30, TimeUnit.SECONDS)
            client.protocols(Collections.singletonList(Protocol.HTTP_1_1))
        }.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: Call.Factory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .callFactory(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
    }

    @Provides
    @Singleton
    fun provideDemoService(retrofit: Retrofit): DemoServiceRetrofit = retrofit.create(DemoServiceRetrofit::class.java)

}


