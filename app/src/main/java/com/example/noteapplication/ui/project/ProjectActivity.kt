package com.example.noteapplication.ui.project

import android.os.Handler
import android.view.LayoutInflater
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapplication.base.BaseActivity
import com.example.noteapplication.base.ItemSimpleTouch
import com.example.noteapplication.base.ProjectEvent
import com.example.noteapplication.data.model.Project
import com.example.noteapplication.databinding.ActivityMainBinding
import com.example.noteapplication.databinding.BottomSheetColorPickerBinding
import com.example.noteapplication.databinding.ViewBottomTabBinding
import com.example.noteapplication.ui.create_project.CreateProjectActivity
import com.example.noteapplication.ui.task.NotesListActivity


class ProjectActivity : BaseActivity<ProjectViewModel, ActivityMainBinding>(
        ProjectViewModel::class
), ProjectAdapter.ClickListener {

    override fun getViewBinding(): ActivityMainBinding  = ActivityMainBinding.inflate(layoutInflater)
    private var bottomBinding: ViewBottomTabBinding? = null
    lateinit var adapter: ProjectAdapter

    override fun setupViews() {
        bottomBinding =  ViewBottomTabBinding.inflate(LayoutInflater.from(this))
        setupRecyclerView()
        deleteSwipeAction()
        setupSearchView()
        setupSwipeRefresh()
        addAction()
    }

    private fun setupRecyclerView() {
        adapter = ProjectAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun deleteSwipeAction() {
        val swipeHandler = object : ItemSimpleTouch(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val item = viewModel.project?.get(position)
                viewModel.deleteProject(item?.id)

            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                Handler().postDelayed(Runnable {
                    if (newText == "") {
                        viewModel.fetchProjects()
                    } else {
                        val searchText = newText.toLowerCase()
                        val filtered = mutableListOf<Project>()
                        viewModel.project?.forEach { if (it.name?.toLowerCase()?.contains(searchText)!!) filtered.add(it) }
                        adapter.addItems(filtered)

                    }
                }, 800)
                return false
            }
        })
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchProjects()
        }

        binding.swipeRefreshLayout.setColorSchemeResources(
            android.R.color.holo_red_light)
    }

    private fun addAction() {
        bottomBinding?.btnAdd?.setOnClickListener {
            CreateProjectActivity.instance(this)
        }
    }

    override fun subscribeToLiveData() {
        viewModel.event.observe(this, Observer {
            when (it) {
                is ProjectEvent.ProjectFetched -> {
                    binding.swipeRefreshLayout.isRefreshing = false
                    it.array?.let { array -> adapter.addItems(array) }
                }
            }
        })
    }

    override fun onItemClick(item: Project) {
        NotesListActivity.instance(this, item)
    }
}