package com.example.daggermvvm.data

import io.reactivex.Observable
import retrofit2.Response

interface MoviesDataSource {
    fun getAllMovies () : Observable<MoviesResponse>
}