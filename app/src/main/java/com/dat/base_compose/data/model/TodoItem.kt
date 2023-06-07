package com.dat.base_compose.data.model

data class TodoItem(
    val completed: Boolean?,
    val id: Int?,
    val title: String?,
    val userId: Int?
)