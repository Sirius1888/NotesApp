package com.example.noteapplication.ui.project

import android.os.Handler
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapplication.R
import com.example.noteapplication.base.BaseActivity
import com.example.noteapplication.base.ItemSimpleTouch
import com.example.noteapplication.data.model.Project
import com.example.noteapplication.ui.create_project.CreateProjectActivity
import com.example.noteapplication.ui.task.NotesListActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_bottom_tab.*

class ProjectActivity : BaseActivity<ProjectViewModel>(
        R.layout.activity_main,
        ProjectViewModel::class
), ProjectAdapter.ClickListener {

    lateinit var adapter: ProjectAdapter

    override fun onResume() {
        super.onResume()
        viewModel.fetchProjects()
    }

    override fun setupViews() {
        setupRecyclerView()
        deleteSwipeAction()
        setupSearchView()
        addAction()
    }

    private fun setupRecyclerView() {
        adapter = ProjectAdapter(this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
    }


    private fun deleteSwipeAction() {
        val swipeHandler = object : ItemSimpleTouch(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val item = viewModel.data.value?.get(position)
//                adapter.deleteItem(position)
                viewModel.deleteProject(item?.id)

            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recycler_view)
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
        btn_add.setOnClickListener {
            CreateProjectActivity.instance(this)
        }
    }

    override fun subscribeToLiveData() {
        viewModel.data.observe(this, Observer {
            adapter.addItems(it)
        })
    }

    override fun onItemClick(item: Project) {
        NotesListActivity.instance(this, item)
    }
}