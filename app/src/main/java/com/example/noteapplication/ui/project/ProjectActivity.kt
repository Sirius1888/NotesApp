package com.example.noteapplication.ui.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapplication.R
import com.example.noteapplication.data.model.Project
import com.example.noteapplication.data.network.RequestResult
import com.example.noteapplication.repository.ProjectRepository
import kotlinx.android.synthetic.main.activity_main.*

class ProjectActivity : AppCompatActivity(), RequestResult {

    private lateinit var adapter: ProjectAdapter
    private lateinit var repository: ProjectRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        setupRepository()
    }

    private fun setupRecyclerView() {
        adapter = ProjectAdapter()
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
    }

    private fun setupRepository() {
        repository = ProjectRepository(this)
        repository.fetchProjects()
    }

    override fun <T> onSuccess(result: T) {
        val data = result as MutableList<Project>
        adapter.addItems(data)
    }

    override fun onFailure(t: Throwable) {
        Toast.makeText(this, t.message, Toast.LENGTH_LONG).show()
    }

}