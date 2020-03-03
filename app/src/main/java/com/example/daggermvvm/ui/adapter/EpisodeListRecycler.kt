package com.moducode.daggerexample.ui.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.daggermvvm.R
import com.example.daggermvvm.data.response.Results
import kotlinx.android.synthetic.main.list_item.view.*

class EpisodeListRecycler(private val data: List<Results>)
    : RecyclerView.Adapter<EpisodeListRecycler.EpisodeHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return EpisodeHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: EpisodeHolder, position: Int) {
        holder.bind(data[position])
    }

    class EpisodeHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(results: Results) {
            itemView.tv_episode_title.text = results.overview
//            Glide.with(itemView.context).applyDefaultRequestOptions(RequestOptions().override(100).fitCenter()).load("http://image.tmdb.org/t/p/w500"+results.poster_path).into(itemView.iv_episode_image)
            Glide.with(itemView.context).load("http://image.tmdb.org/t/p/w500"+results.poster_path).placeholder(R.drawable.ic_launcher_background).fitCenter().into(itemView.iv_episode_image)
//            itemView.setOnClickListener { func(results) }
        }
    }
}