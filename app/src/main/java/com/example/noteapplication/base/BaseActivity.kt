package com.example.noteapplication.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.example.noteapplication.showToast
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

abstract class BaseActivity<VM : ViewModel, VB: ViewBinding>(
        private val clazz: KClass<VM>
) : AppCompatActivity() {

    lateinit var binding: VB
    abstract fun getViewBinding(): VB
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        viewModel = getViewModel(clazz = clazz)
        setupViews()
        subscribeToLiveData()
        subscribeToMessages()
    }

    private fun subscribeToMessages() {
//        viewModel.message.observeForever {
//            showToast(it)
//        }
    }

    abstract fun setupViews()
    abstract fun subscribeToLiveData()
}