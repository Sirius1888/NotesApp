package com.example.noteapplication.repository

import androidx.lifecycle.MutableLiveData
import com.example.noteapplication.data.local.SharedPreference
import com.example.noteapplication.data.model.Project
import com.example.noteapplication.data.network.ProjectApi
import com.example.noteapplication.data.network.ResponseResult
import com.example.noteapplication.data.network.RetrofitClient
import com.example.noteapplication.di.SharedPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


interface ProjectRepository {
    fun fetchProjects(): MutableLiveData<ResponseResult<MutableList<Project>>>
    fun createProject(name: String): MutableLiveData<ResponseResult<Project>>
}

class ProjectRepositorImpl(
        private val api: ProjectApi
) : ProjectRepository {

    override fun fetchProjects(): MutableLiveData<ResponseResult<MutableList<Project>>> {
        val data: MutableLiveData<ResponseResult<MutableList<Project>>>
                = MutableLiveData(ResponseResult.loading())
        api.fetchProjects().enqueue(object : Callback<MutableList<Project>> {
            override fun onFailure(call: Call<MutableList<Project>>, t: Throwable) {
                data.value = ResponseResult.error(t.message)
            }

            override fun onResponse(call: Call<MutableList<Project>>, response: Response<MutableList<Project>>) {
                data.value = ResponseResult.success(response.body())
            }
        })
        return data
    }

    override fun createProject(name: String): MutableLiveData<ResponseResult<Project>> {
        val project = Project(name = name, color = 38)
        val data: MutableLiveData<ResponseResult<Project>>
                = MutableLiveData(ResponseResult.loading())
        api.createProject(project).enqueue(object : Callback<Project> {
            override fun onFailure(call: Call<Project>, t: Throwable) {
                data.value = ResponseResult.error(t.message)
            }

            override fun onResponse(call: Call<Project>, response: Response<Project>) {
                data.value = ResponseResult.success(response.body())
            }
        })
        return data
    }

}