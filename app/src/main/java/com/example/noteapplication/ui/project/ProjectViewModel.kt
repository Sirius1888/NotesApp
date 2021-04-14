package com.example.noteapplication.ui.project

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noteapplication.data.model.Project
import com.example.noteapplication.repository.ProjectRepositorImpl
import com.example.noteapplication.repository.ProjectRepository

class ProjectViewModel : ViewModel() {

    private val repository = ProjectRepositorImpl()
    val data: MutableLiveData<MutableList<Project>>?
    val message: MutableLiveData<String>?

    init {
        data = MutableLiveData()
        message = MutableLiveData()
        subscribeToData()
        subscribeToMessage()
        repository.fetchProjects()
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