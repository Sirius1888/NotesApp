package com.example.noteapplication.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel<EVENT: BaseEvent> : ViewModel() {


    val message = MutableLiveData<String>()

    val toast: String = "Hello"

    val loading = MutableLiveData<Boolean>()
    val event = MutableLiveData<EVENT>()

}