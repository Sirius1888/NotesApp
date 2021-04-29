package com.example.noteapplication.ui.project

import android.os.Handler
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapplication.R
import com.example.noteapplication.base.BaseActivity
import com.example.noteapplication.data.model.Project
import com.example.noteapplication.ui.create_project.CreateProjectActivity
import com.example.noteapplication.ui.task.TaskListActivity
import kotlinx.android.synthetic.main.activity_main.*

class ProjectActivity : BaseActivity<ProjectViewModel>(
        R.layout.activity_main,
        ProjectViewModel::class.java
), ProjectAdapter.ClickListener {

    lateinit var adapter: ProjectAdapter

    override fun setupViews() {
        setupRecyclerView()
        setupSearchView()
        addAction()
    }

    private fun setupRecyclerView() {
        adapter = ProjectAdapter(this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
    }

    private fun setupSearchView() {
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                Handler().postDelayed(Runnable {
                    if (newText == "") {
                        adapter.addItems(viewModel.project)
                    } else {

                        val searchText = newText.toLowerCase()
                        val filtered = mutableListOf<Project>()
                        viewModel.project.forEach { if (it.name?.toLowerCase()?.contains(searchText)!!) filtered.add(it) }
                        adapter.addItems(filtered)

                    }
                }, 800)
                return false
            }
        })
    }

    private fun addAction() {

    }

    override fun subscribeToLiveData() {
        viewModel.data?.observe(this, Observer {
            adapter.addItems(it)
        })
    }

    override fun onItemClick(item: Project) {
        TaskListActivity.instance(this, item)
    }
}


fun main() {
    val person = Elf().setPersonDefaults()
}

open class Person {
    open fun setPersonDefaults() {
        print("PERSON: SET DEFAULTS \n")
    }
}

class Elf : Person() {
    override fun setPersonDefaults() {
        super.setPersonDefaults()
        print("PERSON: WITH MODIFY DATA \n")
    }
}