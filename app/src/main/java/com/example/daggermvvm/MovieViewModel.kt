package com.example.daggermvvm

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.daggermvvm.data.MovieRepository
import com.example.daggermvvm.data.MoviesResponse
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository) {
    var disposable = CompositeDisposable()
    val detailedReportResponse = MutableLiveData<MoviesResponse>()

    @SuppressLint("CheckResult")
    fun getMovieApi() {
         disposable.add(movieRepository.getAllMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                detailedReportResponse.value = it
            },{
                Log.d("error",it.toString())
            }))
    }
}