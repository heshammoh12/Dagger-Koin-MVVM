package com.example.daggermvvm

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.daggermvvm.data.MovieRepository
import com.example.daggermvvm.data.response.MoviesResponse
import androidx.lifecycle.viewModelScope
import com.example.daggermvvm.data.FetchingMoviesError
import com.example.daggermvvm.data.response.APIResult
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
    val detailedReportResponse = MutableLiveData<MoviesResponse>()
    val progressBar = MutableLiveData<Boolean>()

    @SuppressLint("CheckResult")
    fun getMovieApi() {
        viewModelScope.launch {
            progressBar.value = true
            when (val result = movieRepository.getAllMovies()) {
                is APIResult.Success -> {
                    detailedReportResponse.value = result.data
                }
                is APIResult.Error -> {
                    progressBar.value = false
//                        throw FetchingMoviesError(result.errorBody.toString(), null)
                }
            }
        }
    }
}