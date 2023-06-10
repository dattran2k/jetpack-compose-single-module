package com.dat.base_compose.core.di

import android.content.Context
import com.dat.base_compose.core.datastore.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun providesDataStore(
        @ApplicationContext context: Context,
    ): DataStoreManager {
        return DataStoreManager(context)
    }

}
