@file:OptIn(ExperimentalCoroutinesApi::class)

package com.dat.base_compose.data.repository.test

import com.dat.base_compose.core.common.Resource
import com.dat.base_compose.data.model.TodoItem
import com.dat.base_compose.data.respository.todo.TodoRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class TodoRepositoryTest {

    @Test
    fun `getDataWithFlow() should return success resource with todo items`() = runTest {
        // Arrange
        val todoItems = listOf(
            TodoItem(true, 1, "Task 1", 1),
            TodoItem(false, 2, "Task 2", 2)
        )
        val repository = mock(TodoRepository::class.java)
        `when`(repository.getTodos()).thenReturn(flow { emit(Resource.Success(todoItems)) })

        // Act
        val resultFlow: Flow<Resource<List<TodoItem>>> = repository.getTodos()

        // Assert
        resultFlow.collect { resource ->
            assertEquals(Resource.Success(todoItems), resource)
        }
    }

    @Test
    fun `getDataWithFlow() should return loading resource`() = runTest {
        // Arrange
        val repository = mock(TodoRepository::class.java)
        `when`(repository.getTodos()).thenReturn(flow { emit(Resource.Loading) })

        // Act
        val resultFlow: Flow<Resource<List<TodoItem>>> = repository.getTodos()

        // Assert
        resultFlow.collect { resource ->
            assertEquals(Resource.Loading, resource)
        }
    }

    @Test
    fun `getDataWithFlow() should return error resource`() = runTest {
        // Arrange
        val errorMessage = "An error occurred"
        val errorCode = 500
        val repository = mock(TodoRepository::class.java)
        `when`(repository.getTodos()).thenReturn(flow {
            emit(
                Resource.Error(
                    errorMessage,
                    errorCode
                )
            )
        })

        // Act
        val resultFlow: Flow<Resource<List<TodoItem>>> = repository.getTodos()

        // Assert
        resultFlow.collect { resource ->
            assertEquals(Resource.Error<List<TodoItem>>(errorMessage, errorCode), resource)
        }
    }
}