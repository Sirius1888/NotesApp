package com.example.noteapplication

import android.app.Application
import com.example.noteapplication.di.networkRepository
import com.example.noteapplication.di.repositoryModule
import com.example.noteapplication.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.loadKoinModules

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            inject()
        }
    }

    fun inject() = loadKoinModules

    private val loadKoinModules by lazy {
        loadKoinModules(listOf(viewModelModule, repositoryModule, networkRepository))
    }
}