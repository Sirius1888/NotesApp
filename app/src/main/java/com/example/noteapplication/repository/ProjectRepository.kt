package com.example.noteapplication.repository

import com.example.noteapplication.data.model.Project
import com.example.noteapplication.data.network.RequestResult
import com.example.noteapplication.data.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProjectRepository(var callback: RequestResult) {

    val api = RetrofitClient().projectApi

    fun fetchProjects() {
        api.fetchProjects().enqueue(object : Callback<MutableList<Project>> {
            override fun onFailure(call: Call<MutableList<Project>>, t: Throwable) {
                callback.onFailure(t)
            }

            override fun onResponse(call: Call<MutableList<Project>>, response: Response<MutableList<Project>>) {
                callback.onSuccess(response.body())
            }
        })
    }
}