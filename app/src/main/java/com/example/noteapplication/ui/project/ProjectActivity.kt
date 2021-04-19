package com.example.noteapplication.ui.project

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapplication.R
import com.example.noteapplication.base.BaseSearchAdapterActivity
import com.example.noteapplication.data.model.Project
import com.example.noteapplication.data.network.RequestResult
import com.example.noteapplication.repository.ProjectRepository
import com.example.noteapplication.ui.task.TaskListActivity
import kotlinx.android.synthetic.main.activity_main.*

class ProjectActivity : BaseSearchAdapterActivity<ProjectViewModel>(
        R.layout.activity_main,
        ProjectViewModel::class.java
), ProjectAdapter.ClickListener {

    private lateinit var adapter: ProjectAdapter

    override fun setupViews() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = ProjectAdapter(this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
    }

    override fun subscribeToLiveData() {
        viewModel.data?.observe(this, Observer {
            if (it != null) adapter.addItems(it)
        })
    }

    override fun onItemClick(item: Project) {
        TaskListActivity.start(this, item)
    }
}













interface BaseFuctions {
//    var name: String
    fun run()
    fun rest()
    fun eat()

}

class Animals: BaseFuctions {
    var height = 0
    var weight = 0
    var type = " Позвоночные"
//            ...


    override fun run() {

    }

    override fun rest() {

    }

    override fun eat() {

    }
}

class Main {
    var personMenu = CreateCharacter(Vampire())
}

class CreateCharacter(character: BaseСharacter) {

}

abstract class BaseСharacter {

    var gender = "XX"
    abstract var language: String
    abstract fun say()

    fun generateGender() {
//        Функция генерирования гендера
    }
}


abstract class BaseHuman: BaseСharacter() {
//    ..
}

open class Human : BaseFuctions, BaseHuman() {

    init {
        generateGender()
    }

    var height = 0
    var weight = 0
    var age = 0

    override fun run() {

    }

    override fun rest() {

    }

    override fun eat() {

    }

    override var language: String = "RU"
    override fun say() {

    }
}

class Vampire : Human() {

}

class Alien : BaseСharacter() {
    override var language: String = "ALIENTES"

    override fun say() {
        "∆ˆˆˆºœ∂ç˜˜≤ç≥≤å∂"
    }

}

class Ork : BaseСharacter() {
    override var language: String = "ORKSKII"

    override fun say() {
        "dfiojafjasfjlkj"
    }

}

