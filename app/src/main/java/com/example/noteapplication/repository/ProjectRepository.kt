package com.example.noteapplication.repository

import androidx.lifecycle.MutableLiveData
import com.example.noteapplication.data.model.Project
import com.example.noteapplication.data.network.RequestResult
import com.example.noteapplication.data.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


interface ProjectRepository {
    fun fetchProjects()
}

class ProjectRepositorImpl: ProjectRepository {

    val api = RetrofitClient().projectApi
    val data: MutableLiveData<MutableList<Project>>? = MutableLiveData()
    val message: MutableLiveData<String>? = MutableLiveData()
    override fun fetchProjects() {
        api.fetchProjects().enqueue(object : Callback<MutableList<Project>> {
            override fun onFailure(call: Call<MutableList<Project>>, t: Throwable) {
                message?.value = t.message
            }

            override fun onResponse(call: Call<MutableList<Project>>, response: Response<MutableList<Project>>) {
                data?.value = response.body()
            }
        })
    }
}