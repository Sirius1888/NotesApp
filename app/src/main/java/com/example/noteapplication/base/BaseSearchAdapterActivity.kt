package com.example.noteapplication.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.noteapplication.ui.project.ProjectAdapter
import com.example.noteapplication.ui.project.ProjectViewModel
import kotlin.reflect.KClass

abstract class BaseSearchAdapterActivity<T : ViewModel>(
        private val layoutId: Int,
        val vmClass : Class<T>
)
    : AppCompatActivity() {

    lateinit var viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        viewModel = ViewModelProvider(this).get(vmClass)
        setupViews()
        subscribeToLiveData()
    }

    abstract fun setupViews()
    abstract fun subscribeToLiveData()
}
