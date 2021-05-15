package com.example.noteapplication.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.noteapplication.showToast
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

abstract class BaseActivity<VM : BaseViewModel>(
        private val layoutId: Int,
        private val clazz: KClass<VM>
) : AppCompatActivity() {

    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        viewModel = getViewModel(clazz = clazz)
        setupViews()
        subscribeToLiveData()
        subscribeToMessages()
    }

    private fun subscribeToMessages() {
        viewModel.message.observeForever {
            showToast(it)
        }
    }

    abstract fun setupViews()
    abstract fun subscribeToLiveData()
}

fun main() {
   val a = A(B(C()), D())
}

class A(b: B, d: D) {

}

class B(c: C) {

}

class C {

}

class D {

}