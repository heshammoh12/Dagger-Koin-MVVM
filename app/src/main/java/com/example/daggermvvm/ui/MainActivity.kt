package com.example.daggermvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.daggermvvm.MovieViewModel
import com.example.daggermvvm.R
import com.example.daggermvvm.dagger.ContextModule
import com.example.daggermvvm.dagger.DaggerAppComponent
import com.example.daggermvvm.data.FetchingMoviesError
import com.moducode.daggerexample.ui.adapter.EpisodeListRecycler
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var movieViewModel:MovieViewModel
    lateinit var recyclerAdapter:EpisodeListRecycler
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar = findViewById(R.id.progressBar)

        DaggerAppComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
            .movieViewModelInjection(this)

        getMovies()

        val lm = LinearLayoutManager(this)
        activity_main_recycler_movies.run {
            layoutManager = lm
            addItemDecoration(DividerItemDecoration(context, lm.orientation))
        }

        movieViewModel.detailedReportResponse.observe(this, Observer {
            recyclerAdapter = EpisodeListRecycler(it.results).apply {
                notifyDataSetChanged()
            }
            progressBar.visibility = View.GONE
            activity_main_recycler_movies.adapter = recyclerAdapter
        })

        movieViewModel.progressBar.observe(this, Observer {
            progressBar.visibility =  if(it) View.VISIBLE else View.GONE
        })

    }

    private fun getMovies() {
        try {
            movieViewModel.getMovieApi()
        }catch (error: FetchingMoviesError){
            Toast.makeText(this, error.message,Toast.LENGTH_LONG).show()
        }
    }
}
