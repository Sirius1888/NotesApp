package com.example.noteapplication.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.noteapplication.showToast

abstract class BaseActivity<VM : BaseViewModel>(
        private val layoutId: Int
) : AppCompatActivity() {

    abstract val viewModel: VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        setupViews()
        subscribeToLiveData()
    }

    private fun subscribeToMessages() {
        viewModel.message.observeForever {
            showToast(it)
        }
    }

    abstract fun setupViews()
    abstract fun subscribeToLiveData()
}