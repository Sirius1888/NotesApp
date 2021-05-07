package com.example.noteapplication.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext

class RetrofitClient(private val okHttpClient: OkHttpClient) {

    fun provideRetrofit() = Retrofit.Builder()
        .baseUrl("https://api.todoist.com/rest/v1/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideProjectApi(retrofit: RetrofitClient) = retrofit.provideRetrofit().create(ProjectApi::class.java)
fun provideTasksApi(retrofit: RetrofitClient) = retrofit.provideRetrofit().create(TaskApi::class.java)

fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient()
            .newBuilder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
}

fun provideHttpLoginingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}
