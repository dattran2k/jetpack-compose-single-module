package com.dat.base_compose.data.respository.todo


import com.dat.base_compose.core.common.Resource
import com.dat.base_compose.data.model.TodoItem
import kotlinx.coroutines.flow.Flow


interface TodoRepository {
    fun getDataWithFlow(): Flow<Resource<List<TodoItem>>>
}