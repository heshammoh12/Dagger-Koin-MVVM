package com.example.daggermvvm.dagger

import com.example.daggermvvm.MovieViewModel
import com.example.daggermvvm.data.MovieRepository
import com.example.daggermvvm.service.MovieServiceApi
import com.example.daggermvvm.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MovieServiceApiModule::class, MovieRepositoryModule::class, MovieViewModelModule::class])
interface AppComponent {
    fun movieServiceApi(): MovieServiceApi
    fun movieRepo(): MovieRepository
    fun movieViewModel(): MovieViewModel
    fun movieViewModelInjection(mainActivity: MainActivity)
}