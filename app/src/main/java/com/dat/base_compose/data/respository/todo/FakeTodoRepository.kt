package com.dat.base_compose.data.respository.todo

import com.dat.base_compose.core.common.Resource
import com.dat.base_compose.data.model.TodoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeTodoRepository : TodoRepository {
    var fakeResult = MutableStateFlow<Resource<List<TodoItem>>>(Resource.Loading)

    fun createInitFlow() {
        fakeResult = MutableStateFlow(Resource.Loading)
    }

    suspend fun success(): List<TodoItem> {
        val todoItems = listOf(
            TodoItem(true, 1, "Title 1", 1),
            TodoItem(false, 2, "Title 2", 1)
        )
        fakeResult.emit(Resource.Success(todoItems))
        return todoItems
    }

    suspend fun loading() {
        fakeResult.emit(Resource.Loading)
    }

    suspend fun error(): Resource.Error<List<TodoItem>> {
        val error = Resource.Error<List<TodoItem>>("Fail")
        fakeResult.emit(error)
        return error
    }

    override fun getTodos(): Flow<Resource<List<TodoItem>>> {
        return fakeResult
    }
}
