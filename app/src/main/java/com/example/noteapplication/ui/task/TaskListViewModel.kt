package com.example.noteapplication.ui.task

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noteapplication.data.model.Project
import com.example.noteapplication.data.model.Task
import com.example.noteapplication.repository.TaskRepositoryImpl

class TaskListViewModel : ViewModel() {

    private val repository = TaskRepositoryImpl()

    val data: MutableLiveData<MutableList<Task>>?
    val message: MutableLiveData<String>?

    var project: Project? = null

    init {
        data = MutableLiveData()
        message = MutableLiveData()
        subscribeToData()
        subscribeToMessage()
        repository.fetchAllProjectsTasks(project?.id)
    }

    private fun subscribeToData() {
        repository.data?.observeForever {
            data?.value = it
        }
    }

    private fun subscribeToMessage() {
        repository.message?.observeForever {
            message?.value = it
        }
    }
}




