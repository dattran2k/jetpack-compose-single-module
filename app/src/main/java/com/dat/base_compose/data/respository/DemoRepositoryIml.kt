package com.dat.base_compose.data.respository

import com.dat.base_compose.core.common.Resource
import com.dat.base_compose.data.network.ApiDataSource
import com.dat.base_compose.core.common.flowSafeApiCall
import com.dat.base_compose.data.model.TodoItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
Pick one style
 */
class DemoRepositoryIml @Inject constructor(
    private val apiDataSource: ApiDataSource,
    private val dispatcher: CoroutineDispatcher,
) : DemoRepository {
    override fun getDataWithFlow(): Flow<Resource<List<TodoItem>>> {
        return flowSafeApiCall(dispatcher) {
            apiDataSource.getTodos()
        }
    }
}
