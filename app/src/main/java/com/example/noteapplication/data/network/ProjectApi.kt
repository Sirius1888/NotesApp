package com.example.noteapplication.data.network

import com.example.noteapplication.data.model.Project
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers


interface ProjectApi {

    @Headers(
            "Authorization: Bearer 18d41187422aa8a8949e8a12f437b961c34b0dce",
            "client_id: e71d58b4ed1b48abb2b8530df55be03f",
            "client_secret: 324fa28e17164dc8b799b373f3480806"
    )
    @GET("projects")
    fun fetchProjects(): Call<MutableList<Project>>

}