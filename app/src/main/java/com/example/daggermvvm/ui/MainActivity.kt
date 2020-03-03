package com.example.daggermvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.daggermvvm.MovieViewModel
import com.example.daggermvvm.R
import com.example.daggermvvm.data.MovieRepository
import com.moducode.daggerexample.ui.adapter.EpisodeListRecycler
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val movieViewModel: MovieViewModel by viewModel()
    lateinit var recyclerAdapter: EpisodeListRecycler
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar = findViewById(R.id.progressBar)

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
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

    }
}
