package com.example.daggermvvm

import android.app.Activity
import android.app.Application
import com.example.daggermvvm.data.MovieRepository
import com.example.daggermvvm.network.API
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class AppClass: Application() {


    override fun onCreate() {
        super.onCreate()

        val moduel = module {
            single{(MovieRepository(get()))}
            single { API.moviesAPI }
            viewModel { MovieViewModel(get()) }
        }

        // start Koin context
        startKoin {
            androidContext(this@AppClass)
            androidLogger()
            modules(listOf(moduel))
        }
    }

    companion object {
        fun getApplication(activity: Activity) = activity.application as AppClass
    }
}