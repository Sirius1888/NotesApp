package com.example.noteapplication.data.network

interface RequestResult {
    fun <T>onSuccess(result : T)
    fun onFailure(t: String?)
}