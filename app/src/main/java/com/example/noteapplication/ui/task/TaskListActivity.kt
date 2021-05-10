package com.example.noteapplication.ui.task

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapplication.R
import com.example.noteapplication.base.BaseActivity
import com.example.noteapplication.data.model.Project
import com.example.noteapplication.data.model.Task
import kotlinx.android.synthetic.main.activity_task_list.*
import org.koin.java.KoinJavaComponent.inject

class TaskListActivity : BaseActivity<TaskListViewModel>(
        R.layout.activity_task_list,
        TaskListViewModel::class
), TaskAdapter.ClickListener {


    private lateinit var adapter: TaskAdapter

    override fun setupViews() {
        getIntentData()
        setupRecyclerView()
    }

    private fun getIntentData() {
        viewModel.project = intent.getSerializableExtra(PROJECT_KEY) as Project?
    }

    private fun setupRecyclerView() {
        adapter = TaskAdapter(this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
    }

    override fun subscribeToLiveData() {
        subscribeToData()
    }

    private fun subscribeToData() {
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

        fun instance(context: Context, item: Project) {
            val intent = Intent(context, TaskListActivity::class.java)
            intent.putExtra(PROJECT_KEY, item)
            context.startActivity(intent)
        }
    }
}
