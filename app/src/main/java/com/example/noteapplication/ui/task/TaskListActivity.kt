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
import com.example.noteapplication.showToast
import com.example.noteapplication.ui.project.ProjectActivity
import kotlinx.android.synthetic.main.activity_task_list.*

class TaskListActivity : AppCompatActivity(), RequestResult, TaskAdapter.ClickListener {

    //Добавить 2-3 метода для экстеншинов
    //Исправить смену состояния CheckBox в TaskAdapter при нажатии

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
        if (result is String) {
            printSuccessedRequest(result)
        } else if (result is MutableList<*>) {
            val data = result as MutableList<Task>
            adapter.addItems(data)
        }
    }

    override fun onFailure(t: String?) {
        showToast(t)
    }

    fun printSuccessedRequest(message: String) {
        when(message) {
            "changed state of task" -> showToast("Таск завершен")
            "deleted task" -> showToast("Таск удален")
        }
    }

    override fun onItemClick(item: Task) {

    }

    override fun onCheckedClick(item: Task) {
        repository.changeStateOfTask(item.id)
    }

    override fun onRemoveItemClick(item: Task, position: Int) {
        repository.deleteTask(item.id)
    }

}