package com.example.noteapplication.repository

import androidx.lifecycle.MutableLiveData
import com.example.noteapplication.data.model.Task
import com.example.noteapplication.data.network.ResponseResult
import com.example.noteapplication.data.network.ResponseResultStatus
import com.example.noteapplication.data.network.RetrofitClient
import com.example.noteapplication.data.network.TaskApi
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface TaskRepository {
    fun fetchAllProjectsTasks(id: Long?): MutableLiveData<ResponseResult<MutableList<Task>>>
    fun createNote(dto: Task): MutableLiveData<ResponseResult<Task>>
    fun changeStateOfTask(id: Long?)
    fun deleteTask(id: Long?)
}

class TaskRepositoryImpl(
        private val api: TaskApi
) : TaskRepository {

    override fun fetchAllProjectsTasks(id: Long?): MutableLiveData<ResponseResult<MutableList<Task>>> {
        val data: MutableLiveData<ResponseResult<MutableList<Task>>> = MutableLiveData(ResponseResult.loading())
        api.fetchTasks(id).enqueue(object : Callback<MutableList<Task>> {
            
            override fun onFailure(call: Call<MutableList<Task>>, t: Throwable) {
                data.value = ResponseResult.error(t.message)
            }

            override fun onResponse(call: Call<MutableList<Task>>, response: Response<MutableList<Task>>) {
                data.value =
                        if (response.isSuccessful) ResponseResult.success(response.body())
                        else ResponseResult.error(response.message())
            }
        })
        return data
    }

    override fun createNote(dto: Task): MutableLiveData<ResponseResult<Task>> {
        val data: MutableLiveData<ResponseResult<Task>> = MutableLiveData(ResponseResult.loading())
        api.createNote(dto).enqueue(object : Callback<Task> {
            override fun onFailure(call: Call<Task>, t: Throwable) {
                data.value = ResponseResult.error(t.message)
            }

            override fun onResponse(call: Call<Task>, response: Response<Task>) {
                data.value =
                        if (response.isSuccessful) ResponseResult.success(response.body())
                        else ResponseResult.error(response.message())
            }
        })
        return data
    }

    override fun changeStateOfTask(id: Long?) {
        api.changeStateOfTask(id).enqueue(object : Callback<Unit> {
            override fun onFailure(call: Call<Unit>, t: Throwable) {
//                callback.onFailure(t.message)
            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
//                if (response.isSuccessful) callback.onSuccess("changed state of task")
//                else callback.onFailure(response.message())
            }
        })
    }

    override fun deleteTask(id: Long?) {
        api.deleteTask(id).enqueue(object : Callback<Unit> {
            override fun onFailure(call: Call<Unit>, t: Throwable) {
//                callback.onFailure(t.message)
            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
//                if (response.isSuccessful) callback.onSuccess("deleted task")
//                else callback.onFailure(response.message())
            }
        })
    }

}
