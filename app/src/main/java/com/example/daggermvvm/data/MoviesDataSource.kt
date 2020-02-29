package com.example.daggermvvm.data

import com.example.daggermvvm.data.response.APIResult
import com.example.daggermvvm.data.response.MoviesResponse
import retrofit2.Response

interface MoviesDataSource {
    suspend fun getAllMovies () : APIResult<MoviesResponse>
}