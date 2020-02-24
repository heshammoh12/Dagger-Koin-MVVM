package com.example.daggermvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.daggermvvm.MovieViewModel
import com.example.daggermvvm.R
import com.example.daggermvvm.dagger.ContextModule
import com.example.daggermvvm.dagger.DaggerAppComponent
import com.moducode.daggerexample.ui.adapter.EpisodeListRecycler
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var movieViewModel:MovieViewModel
    lateinit var recyclerAdapter:EpisodeListRecycler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerAppComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
            .movieViewModelInjection(this)

        movieViewModel.getMovieApi()

        val lm = LinearLayoutManager(this)
        activity_main_recycler_movies.run {
            layoutManager = lm
            addItemDecoration(DividerItemDecoration(context, lm.orientation))
        }

        movieViewModel.detailedReportResponse.observe(this, Observer {
            recyclerAdapter = EpisodeListRecycler(it.results).apply {
                notifyDataSetChanged()
            }
            activity_main_recycler_movies.adapter = recyclerAdapter
        })
    }
}
