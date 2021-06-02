package com.example.noteapplication.repository

import androidx.lifecycle.MutableLiveData
import com.example.noteapplication.data.model.Project
import com.example.noteapplication.data.network.ProjectApi
import com.example.noteapplication.data.network.ResponseResult
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface ProjectRepository {
    fun fetchProjects(): Observable<MutableList<Project>>
    fun createProject(name: String, color: Int?): MutableLiveData<ResponseResult<Project>>
    fun deleteProject(id: Long?): MutableLiveData<ResponseResult<Int>>
}

class ProjectRepositorImpl(
        private val api: ProjectApi
) : ProjectRepository {

    override fun fetchProjects(): Observable<MutableList<Project>> {
        return api.fetchProjects()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun deleteProject(id: Long?): MutableLiveData<ResponseResult<Int>> {
        val data: MutableLiveData<ResponseResult<Int>>
                = MutableLiveData(ResponseResult.loading())
        api.deleteProject(id).enqueue(object : Callback<Int> {
            override fun onFailure(call: Call<Int>, t: Throwable) {
                data.value = ResponseResult.error(t.message)
            }

            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                if (response.isSuccessful) data.value = ResponseResult.success(response.code())
            }
        })
        return data
    }

    override fun createProject(name: String, color: Int?): MutableLiveData<ResponseResult<Project>> {
        val project = Project(name = name, color = color)
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