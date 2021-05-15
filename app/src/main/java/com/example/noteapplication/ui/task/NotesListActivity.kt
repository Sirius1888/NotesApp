package com.example.noteapplication.ui.task

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapplication.R
import com.example.noteapplication.base.BaseActivity
import com.example.noteapplication.data.model.Project
import com.example.noteapplication.data.model.Task
import kotlinx.android.synthetic.main.activity_notes_list.*
import kotlinx.android.synthetic.main.view_bottom_tab.*

class NotesListActivity : BaseActivity<NotesListViewModel>(
        R.layout.activity_notes_list,
        NotesListViewModel::class
), TaskAdapter.ClickListener {

    private lateinit var adapter: TaskAdapter
    private lateinit var dialog: AlertDialog

    override fun setupViews() {
        getIntentData()
        setupRecyclerView()
        addAction()
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
        subscribeToCreatingNote()
    }

    private fun subscribeToData() {
        viewModel.data?.observe(this, Observer {
            adapter.addItems(it)
        })
    }

    private fun subscribeToCreatingNote() {
        viewModel.noteCreating.observe(this, Observer {
            dialog.dismiss()
            if (it) viewModel.fetchAllProjectsTasks()
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

    private fun addAction() {
        btn_add.setOnClickListener {
            showCreateNoteDialog()
        }
    }

    private fun showCreateNoteDialog() {
        val layoutInflater = LayoutInflater.from(this)
        val view = layoutInflater.inflate(R.layout.dialog_create_notes, null)
        dialog = AlertDialog.Builder(this).create()
        dialog.setView(view)
        dialog.setCancelable(true)

        view.findViewById<Button>(R.id.dialog_add_btn).setOnClickListener {
            val text = view.findViewById<EditText>(R.id.et_input_title).text.toString()
            viewModel.createNote(text)
        }

        view.findViewById<Button>(R.id.dialog_cancel_btn).setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    companion object {
        const val PROJECT_KEY = "PROJECT_KEY"

        fun instance(context: Context, item: Project) {
            val intent = Intent(context, NotesListActivity::class.java)
            intent.putExtra(PROJECT_KEY, item)
            context.startActivity(intent)
        }
    }
}
