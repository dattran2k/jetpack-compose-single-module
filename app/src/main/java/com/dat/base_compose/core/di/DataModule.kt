package com.dat.base_compose.core.di

import com.dat.base_compose.data.respository.todo.TodoRepository
import com.dat.base_compose.data.respository.todo.TodoRepositoryIml
import com.dat.base_compose.data.respository.user.UserDataRepository
import com.dat.base_compose.data.respository.user.UserDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Here are the dependencies which will be injected by hilt
 *
 */
@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsUserRepository(
        userDataRepositoryImpl: UserDataRepositoryImpl
    ): UserDataRepository

    @Binds
    fun bindTodoRepository(
        todoRepositoryImpl: TodoRepositoryIml
    ): TodoRepository

}