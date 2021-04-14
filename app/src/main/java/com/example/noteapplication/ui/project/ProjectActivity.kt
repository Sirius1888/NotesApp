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
import com.example.noteapplication.data.model.Project
import com.example.noteapplication.data.network.RequestResult
import com.example.noteapplication.repository.ProjectRepository
import com.example.noteapplication.ui.task.TaskListActivity
import kotlinx.android.synthetic.main.activity_main.*

class ProjectActivity : AppCompatActivity(), ProjectAdapter.ClickListener {

//    SOLID
//    S - Принцип единой ответственности

    private lateinit var adapter: ProjectAdapter
    private lateinit var viewModel: ProjectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(ProjectViewModel::class.java)
        setupRecyclerView()
        subsctibeToLiveData()
    }

    private fun setupRecyclerView() {
        adapter = ProjectAdapter(this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
    }
    
    private fun subsctibeToLiveData() {
        viewModel.data?.observe(this, Observer {
            if (it != null) adapter.addItems(it)
        })
    }

    override fun onItemClick(item: Project) {
        TaskListActivity.start(this, item)
    }
}