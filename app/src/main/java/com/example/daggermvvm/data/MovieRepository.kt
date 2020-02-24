package com.example.daggermvvm.data

import com.example.daggermvvm.BuildConfig
import com.example.daggermvvm.service.MovieServiceApi
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieServiceApi: MovieServiceApi) :MoviesDataSource {
    override fun getAllMovies(): Observable<MoviesResponse> {
        return movieServiceApi.getTopRatedMovies(BuildConfig.API_KEY)
    }
}