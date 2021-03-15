package com.example.noteapplication.ui.task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapplication.R
import com.example.noteapplication.data.model.Project
import com.example.noteapplication.data.model.Task
import com.example.noteapplication.data.network.RequestResult
import com.example.noteapplication.repository.TaskRepository
import com.example.noteapplication.ui.project.ProjectActivity
import kotlinx.android.synthetic.main.activity_task_list.*

class TaskListActivity : AppCompatActivity(), RequestResult, TaskAdapter.ClickListener {

    private var project = Project()

    private lateinit var adapter: TaskAdapter
    private lateinit var repository: TaskRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)
        getIntentData()
        setupRecyclerView()
        setupRepository()
        fetchData()
    }

    private fun getIntentData() {
        project = intent.getSerializableExtra(ProjectActivity.PROJECT_KEY) as Project
    }

    private fun setupRecyclerView() {
        adapter = TaskAdapter(this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
    }

    private fun setupRepository() {
        repository = TaskRepository(this)
    }

    private fun fetchData() {
        repository.fetchAllProjectsTasks(project.id)
    }

    override fun <T> onSuccess(result: T) {
        val data = result as MutableList<Task>
        adapter.addItems(data)
    }

    override fun onFailure(t: String?) {
        Toast.makeText(this, t, Toast.LENGTH_LONG).show()
    }

    override fun onItemClick(item: Task) {

    }

}