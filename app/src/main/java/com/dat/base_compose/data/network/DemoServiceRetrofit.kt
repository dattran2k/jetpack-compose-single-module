package com.dat.base_compose.data.network

import com.dat.base_compose.data.model.TodoItem
import retrofit2.Response
import retrofit2.http.GET

interface DemoServiceRetrofit {
    @GET("/todos")
    suspend fun getTodos(): Response<List<TodoItem>>

}