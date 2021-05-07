package com.example.noteapplication.ui.task

import androidx.lifecycle.MutableLiveData
import com.example.noteapplication.base.BaseViewModel
import com.example.noteapplication.data.model.Project
import com.example.noteapplication.data.model.Task
import com.example.noteapplication.data.network.ResponseResultStatus
import com.example.noteapplication.repository.TaskRepositoryImpl

class TaskListViewModel(
        private val repository: TaskRepositoryImpl
) : BaseViewModel() {

    val data: MutableLiveData<MutableList<Task>>? = MutableLiveData()
    var project: Project? = null

    init {
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




