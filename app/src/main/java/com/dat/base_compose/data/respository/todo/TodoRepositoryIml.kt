package com.dat.base_compose.data.respository.todo

import com.dat.base_compose.core.common.Resource
import com.dat.base_compose.data.network.ApiDataSource
import com.dat.base_compose.core.common.flowSafeApiCall
import com.dat.base_compose.core.di.Dispatcher
import com.dat.base_compose.core.di.NiaDispatchers
import com.dat.base_compose.data.model.TodoItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class TodoRepositoryIml @Inject constructor(
    private val apiDataSource: ApiDataSource,
    @Dispatcher(NiaDispatchers.IO) private val dispatcher: CoroutineDispatcher,
) : TodoRepository {
    override fun getDataWithFlow(): Flow<Resource<List<TodoItem>>> {
        return flowSafeApiCall(dispatcher) {
            apiDataSource.getTodos()
        }
    }
}
