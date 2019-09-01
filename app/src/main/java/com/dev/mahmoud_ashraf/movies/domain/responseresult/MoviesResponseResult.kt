package com.dev.mahmoud_ashraf.movies.domain.responseresult

import com.dev.mahmoud_ashraf.movies.data.model.Movie
import com.dev.mahmoud_ashraf.movies.domain.NetworkState

data class MoviesResponseResult(
    val networkState: NetworkState,
    val movies: List<Movie>? = null
)