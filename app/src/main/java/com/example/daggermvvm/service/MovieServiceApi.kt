package com.example.daggermvvm.service

import com.example.daggermvvm.data.MoviesResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieServiceApi {
    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey:String) : Observable<MoviesResponse>
}