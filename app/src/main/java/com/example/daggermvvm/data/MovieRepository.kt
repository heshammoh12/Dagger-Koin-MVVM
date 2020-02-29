package com.example.daggermvvm.data

import com.example.daggermvvm.BuildConfig
import com.example.daggermvvm.data.response.APIResult
import com.example.daggermvvm.data.response.MoviesResponse
import com.example.daggermvvm.service.MovieServiceApi
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieServiceApi: MovieServiceApi) :
    MoviesDataSource,
    BaseRepository() {
    override suspend fun getAllMovies(): APIResult<MoviesResponse> =
        getAPIResult(safeApiCall{movieServiceApi.getTopRatedMovies(BuildConfig.API_KEY)  })

}

/**
 * Thrown when there was a error fetching a new title
 *
 * @property message user ready error message
 * @property cause the original cause of this exception
 */
class FetchingMoviesError(message: String, cause: Throwable?) : Throwable(message, cause)
