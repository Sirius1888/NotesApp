package com.example.noteapplication.ui.project

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noteapplication.data.model.Project
import com.example.noteapplication.repository.ProjectRepository

class ProjectViewModel : ViewModel() {

    private val repository = ProjectRepository()
    val data: MutableLiveData<MutableList<Project>>?
    val message: MutableLiveData<String>?

    init {
        data = MutableLiveData()
        message = MutableLiveData()
        subsctibeToData()
        subsctibeMessage()
        repository.fetchProjects()
    }

    fun subsctibeToData() {
        repository.data?.observeForever {
            data?.value = it
        }
    }

    fun subsctibeMessage() {
        repository.message?.observeForever {
            message?.value = it
        }
    }
}