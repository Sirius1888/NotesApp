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
import com.example.noteapplication.base.NoteEvent
import com.example.noteapplication.data.model.Project
import com.example.noteapplication.data.model.Task
import com.example.noteapplication.databinding.ActivityNotesListBinding
import com.example.noteapplication.databinding.ViewBottomTabBinding

class NotesListActivity : BaseActivity<NotesListViewModel, ActivityNotesListBinding>(
        NotesListViewModel::class
), TaskAdapter.ClickListener {

    override fun getViewBinding(): ActivityNotesListBinding = ActivityNotesListBinding.inflate(layoutInflater)

    private var bottomBinding: ViewBottomTabBinding? = null

    private lateinit var adapter: TaskAdapter
    private lateinit var dialog: AlertDialog

    override fun setupViews() {
        bottomBinding =  ViewBottomTabBinding.inflate(LayoutInflater.from(this))
        getIntentData()
        setupRecyclerView()
        addAction()
    }

    private fun getIntentData() {
        viewModel.project = intent.getSerializableExtra(PROJECT_KEY) as Project?
    }

    private fun setupRecyclerView() {
        adapter = TaskAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    override fun subscribeToLiveData() {
        subscribeToData()
    }

    private fun subscribeToData() {
        viewModel.event.observe(this, Observer {
            when (it) {
                is NoteEvent.NoteFetched -> adapter.addItems(it.array)
                is NoteEvent.NoteClosed -> adapter.refreshItems(closedPosition)
                is NoteEvent.NoteCreated ->  {
                    dialog.dismiss()
                    viewModel.fetchAllProjectsTasks()
                }
            }
        })
    }

    override fun onItemClick(item: Task) {

    }

    var closedPosition: Int = 0
    override fun onCheckedClick(item: Task, position: Int) {
        viewModel.closeNote(item.id)
        closedPosition = position
    }

    override fun onRemoveItemClick(item: Task, position: Int) {
//        repository.deleteTask(item.id)
    }

    private fun addAction() {
        bottomBinding?.btnAdd?.setOnClickListener {
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
