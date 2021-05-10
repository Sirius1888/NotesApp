package com.example.noteapplication.ui.project

import androidx.lifecycle.MutableLiveData
import com.example.noteapplication.base.BaseViewModel
import com.example.noteapplication.data.model.Project
import com.example.noteapplication.data.network.ResponseResultStatus
import com.example.noteapplication.repository.ProjectRepositorImpl

class ProjectViewModel(private val repository: ProjectRepositorImpl) : BaseViewModel() {

    var project = mutableListOf<Project>()
    val data = MutableLiveData<MutableList<Project>>()

    fun fetchProjects() {
        repository.fetchProjects().observeForever {
            when (it.status) {
                ResponseResultStatus.ERROR -> {
                    message.value = it.message
                    loading.value = false
                }
                ResponseResultStatus.SUCCESS -> {
                    data.value = it.result
                    loading.value = false
                }
                ResponseResultStatus.LOADING -> loading.value = true
            }
        }
    }

    fun deleteProject(id: Long?) {
        repository.deleteProject(id).observeForever {
            when (it.status) {
                ResponseResultStatus.ERROR -> {
                    message.value = it.message
                    loading.value = false
                }
                ResponseResultStatus.SUCCESS -> {
                    handleResult(it.result)
                    loading.value = false
                }
                ResponseResultStatus.LOADING -> loading.value = true
            }
        }
    }

    private fun handleResult(code: Int?) {
        if (code == 204) message.value = "Проект успешно удален"
    }

}

fun main() {
    val car = Car(Engine(Gazoline()), Body(), Driver(License(19, "KG")))
}

class Car(engine: Engine, body: Body, driver: Driver) {

}

class Body() {

}

class Driver(license: License) {

    fun getClassOfLicense() {

    }
}

class License(age: Int, gos: String)

class Engine(fuel: Fuel) {

}

open class Fuel() {

}

class Gazoline() : Fuel() {

}

class Electrosity : Fuel() {

}