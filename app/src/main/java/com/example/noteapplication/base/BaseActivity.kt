package com.example.noteapplication.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

interface Base {

    val name: String
    fun setupViews()
    fun subscribeToLiveData()
//    {
//
//    }

    fun multiple(a: Int, b: Int): Int
//    {
//        return a * b
//    }
}

class Mathi {

}



abstract class BaseActivity<VM : ViewModel>(
        private val layoutId: Int,
        private val vmClass : Class<VM>
) : AppCompatActivity() {

    lateinit var viewModel: VM
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

//ademi = 78
//timur = 57
//kanaiym = 51
//aziza = 60
//akyikat = 74