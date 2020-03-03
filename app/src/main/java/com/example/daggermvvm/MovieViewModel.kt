package com.example.daggermvvm

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.daggermvvm.data.MovieRepository
import com.example.daggermvvm.data.response.MoviesResponse
import androidx.lifecycle.viewModelScope
import com.example.daggermvvm.data.response.APIResult
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository: MovieRepository) :
    ViewModel() {
    val _detailedReportResponse = MutableLiveData<MoviesResponse>()
    val detailedReportResponse: LiveData<MoviesResponse> = _detailedReportResponse
    val progressBar = MutableLiveData<Boolean>()

    init {
        viewModelScope.launch {
            progressBar.value = true
            when (val result = movieRepository.getAllMovies()) {
                is APIResult.Success -> {
                    _detailedReportResponse.value = result.data
                }
                is APIResult.Error -> {
                    progressBar.value = false
//                        throw FetchingMoviesError(result.errorBody.toString(), null)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("MovieViewModel","cleared")
    }
}