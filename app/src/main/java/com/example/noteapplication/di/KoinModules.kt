package com.example.noteapplication.di

import com.example.noteapplication.data.network.*
import com.example.noteapplication.repository.ProjectRepositorImpl
import com.example.noteapplication.repository.TaskRepositoryImpl
import com.example.noteapplication.ui.create_project.CreateProjectViewModel
import com.example.noteapplication.ui.project.ProjectViewModel
import com.example.noteapplication.ui.task.NotesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CreateProjectViewModel(get()) }
    viewModel { ProjectViewModel(get()) }
    viewModel { NotesListViewModel(get()) }
}

val repositoryModule = module {
    factory { ProjectRepositorImpl(get()) }
    factory { TaskRepositoryImpl(get()) }
}

val networkRepository = module {
    single { RetrofitClient(get()) }
    single { provideOkHttpClient(get()) }
    single { provideHttpLoginingInterceptor() }
    single { provideProjectApi(get()) }
    single { provideTasksApi(get()) }
}