package com.dev.mahmoud_ashraf.movies.presentation.movies.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.mahmoud_ashraf.movies.data.model.Movie

class MovieAdapter(private var moviesList: ArrayList<Movie>, private val itemClickedCallback: (Movie?) -> Unit)
    : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder.create(parent, itemClickedCallback)

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindTo(moviesList[position])
    }

    override fun getItemCount() = moviesList.size

    fun getMovies() = moviesList

    fun add(items: List<Movie>?) {
        items?.let {
            moviesList.addAll(it)
            notifyDataSetChanged()
        }
    }

    fun clear() {
        moviesList.clear()
        notifyDataSetChanged()
    }
}