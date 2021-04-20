package com.example.noteapplication.ui.task

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapplication.R
import com.example.noteapplication.data.model.Project
import com.example.noteapplication.data.model.Task
import com.example.noteapplication.data.network.RequestResult
import com.example.noteapplication.repository.TaskRepository
import com.example.noteapplication.repository.TaskRepositoryImpl
import com.example.noteapplication.showToast
import com.example.noteapplication.ui.project.ProjectActivity
import com.example.noteapplication.ui.project.ProjectViewModel
import kotlinx.android.synthetic.main.activity_task_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TaskListActivity : AppCompatActivity(), TaskAdapter.ClickListener {

    private lateinit var adapter: TaskAdapter
    private lateinit var viewModel: TaskListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)
        getIntentData()
        viewModel = ViewModelProvider(this).get(TaskListViewModel::class.java)
        setupRecyclerView()
        subscribeToLiveData()
    }

    private fun getIntentData() {
        viewModel.project = intent.getSerializableExtra(PROJECT_KEY) as Project
    }

    private fun setupRecyclerView() {
        adapter = TaskAdapter(this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
    }

    private fun subscribeToLiveData() {
        viewModel.data?.observe(this, Observer {
            adapter.addItems(it)
        })
    }

    override fun onItemClick(item: Task) {

    }

    override fun onCheckedClick(item: Task) {
//        repository.changeStateOfTask(item.id)
    }

    override fun onRemoveItemClick(item: Task, position: Int) {
//        repository.deleteTask(item.id)
    }

    companion object {
        const val PROJECT_KEY = "PROJECT_KEY"

        fun start(context: Context, item: Project) {
            val intent = Intent(context, TaskListActivity::class.java)
            intent.putExtra(PROJECT_KEY, item)
            context.startActivity(intent)
        }
    }
}
