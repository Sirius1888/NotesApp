package com.example.noteapplication.ui.create_project

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noteapplication.repository.ProjectRepositorImpl

class CreateProjectViewModel : ViewModel() {

    private val repository = ProjectRepositorImpl()
    val createResult: MutableLiveData<Boolean>?
    val message: MutableLiveData<String>?

    init {
        createResult = MutableLiveData()
        message = MutableLiveData()
        subscribeToResult()
        subscribeToMessage()
    }

    private fun subscribeToResult() {
        repository.createResult?.observeForever {
            createResult?.postValue(it)
        }
    }

    private fun subscribeToMessage() {
        repository.message?.observeForever {
            message?.value = it
        }
    }

    fun createProject(name: String) {
        if (name.isEmpty()) {
            message?.postValue("Имя проекта не может быть пустым")
            return
        }
        repository.createProject(name)
    }
}