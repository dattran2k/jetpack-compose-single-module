package com.dat.base_compose.di

import android.app.Application
import android.content.Context
import com.dat.base_compose.MyApplication
import com.dat.base_compose.data.remote.data_source.ApiDataSource
import com.dat.base_compose.data.remote.service.DemoService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Here are the dependencies which will be injected by hilt
 *
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    //Gson for converting JSON String to Java Objects
    @Provides
    fun providesGson(): Gson = GsonBuilder().setLenient().create()


    @Provides
    @Singleton
    fun providerContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideApplication(): MyApplication {
        return MyApplication()
    }

    // provide dataSource
    @Provides
    @Singleton
    fun provideApiDatasource(demoService: DemoService, application: Application) =
        ApiDataSource(demoService, application)

}