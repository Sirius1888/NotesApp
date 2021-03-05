package com.example.noteapplication.data.network

interface RequestResult {
    fun onFailure(t: Throwable)
    fun <T>onSuccess(result : T)
}