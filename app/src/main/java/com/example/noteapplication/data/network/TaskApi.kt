package com.example.noteapplication.data.network

import com.example.noteapplication.data.model.Task
import retrofit2.http.GET


interface TaskApi {
    @GET("/tasks")
    fun getAllTasks(): MutableList<Task>
}
