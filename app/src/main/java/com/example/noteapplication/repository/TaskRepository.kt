package com.example.noteapplication.repository

import com.example.noteapplication.data.model.Project
import com.example.noteapplication.data.model.Task
import com.example.noteapplication.data.network.RequestResult
import com.example.noteapplication.data.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TaskRepository(var callback: RequestResult) {

    val api = RetrofitClient().tasksApi

    fun fetchAllProjectsTasks(id: Long?) {
        api.fetchTasks(id).enqueue(object : Callback<MutableList<Project>> {
            override fun onFailure(call: Call<MutableList<Project>>, t: Throwable) {
                callback.onFailure(t.message)
            }

            override fun onResponse(call: Call<MutableList<Project>>, response: Response<MutableList<Project>>) {
                if (response.isSuccessful) callback.onSuccess(response.body())
                else callback.onFailure(response.message())
            }
        })
    }

}
