package com.dat.base_compose.data.respository

import com.dat.base_compose.core.common.Resource
import com.dat.base_compose.data.model.TodoItem
import kotlinx.coroutines.flow.Flow


interface DemoRepository {
    fun getDataWithFlow(): Flow<Resource<List<TodoItem>>>
}