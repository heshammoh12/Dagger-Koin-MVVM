package com.example.daggermvvm.dagger

import com.example.daggermvvm.AppClass
import com.example.daggermvvm.MovieViewModel
import com.example.daggermvvm.data.MovieRepository
import com.example.daggermvvm.ui.MainActivity

fun MainActivity.buildViewModel(): MovieViewModel =
    DaggerAppComponent
        .builder()
        .contextModule(ContextModule(this))
        .build()
        .movieViewModel()

fun MainActivity.buildRepo(): MovieRepository =
    DaggerAppComponent
        .builder()
        .contextModule(ContextModule(this))
        .build()
        .movieRepo()
