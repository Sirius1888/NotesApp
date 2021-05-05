package com.example.noteapplication.ui.task

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noteapplication.data.model.Project
import com.example.noteapplication.data.model.Task
import com.example.noteapplication.data.network.ResponseResultStatus
import com.example.noteapplication.repository.TaskRepositoryImpl

class TaskListViewModel : ViewModel() {

    private val repository = TaskRepositoryImpl()

    val data: MutableLiveData<MutableList<Task>>?
    val message: MutableLiveData<String>?

    var project: Project? = null

    init {
        data = MutableLiveData()
        message = MutableLiveData()
        fetchAllProjectsTasks()
    }

    fun fetchAllProjectsTasks() {
        repository.fetchAllProjectsTasks(project?.id).observeForever {
            when (it.status) {
                ResponseResultStatus.ERROR -> message?.value = it.message
                ResponseResultStatus.SUCCESS -> data?.value = it.result
            }
        }
    }

}




