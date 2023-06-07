package com.dat.base_compose.core.di

import android.content.Context
import com.dat.base_compose.core.datastore.DataStoreManager
import com.dat.base_compose.data.network.ApiDataSource
import com.dat.base_compose.data.respository.DemoRepository
import com.dat.base_compose.data.respository.DemoRepositoryIml
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

/**
 * Here are the dependencies which will be injected by hilt
 *
 */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun providesDataStore(
        @ApplicationContext context: Context,
    ): DataStoreManager {
        return DataStoreManager(context)
    }

    @Provides
    fun bindsDemoRepository(
        dataSource: ApiDataSource,
        @Dispatcher(NiaDispatchers.IO) dispatcher: CoroutineDispatcher,
    ): DemoRepository {
        return DemoRepositoryIml(dataSource, dispatcher)
    }

}