package com.example.noteapplication.ui.create_project

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noteapplication.base.BaseViewModel
import com.example.noteapplication.data.network.ResponseResultStatus
import com.example.noteapplication.data.network.ResponseResultStatus.*
import com.example.noteapplication.repository.ProjectRepositorImpl

class CreateProjectViewModel(
        private val repository: ProjectRepositorImpl
) : BaseViewModel() {

    val createResult = MutableLiveData<Boolean>()

    fun createProject(name: String, color: Int?) {
        if (name.isEmpty()) {
            message.postValue("Имя проекта не может быть пустым")
            return
        }
        if (color == null) {
            message.postValue("Цвет проекта не выбран")
            return
        }
        repository.createProject(name, color).observeForever {
            when(it.status) {
                SUCCESS -> {
                    createResult.value = it.result != null
                    if (it.result != null) message.value = "Проект успешно создан"
                    else message.value = "Ошибка при создании проекта"
                }
                ERROR -> message.value = it.message
            }
        }
    }

}