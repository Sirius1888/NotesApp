package com.example.noteapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.noteapplication.R
import com.example.noteapplication.data.network.RequestResult
import com.example.noteapplication.repository.ProjectRepository

class ProjectActivity : AppCompatActivity(), RequestResult {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ProjectRepository(this).fetchProjects()
    }

    override fun onFailure(t: Throwable) {

    }

    override fun <T> onSuccess(result: T) {

    }

}