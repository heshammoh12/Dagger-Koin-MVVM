package com.example.daggermvvm.network

import com.example.daggermvvm.data.response.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieServiceApi {
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey:String) : Response<MoviesResponse>
}