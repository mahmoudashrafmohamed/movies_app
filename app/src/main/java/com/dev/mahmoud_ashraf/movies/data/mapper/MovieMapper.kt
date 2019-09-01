package com.dev.mahmoud_ashraf.movies.data.mapper

import com.dev.mahmoud_ashraf.movies.data.model.Movie
import com.dev.mahmoud_ashraf.movies.data.model.MovieEntity

class MovieMapper {
    fun fromDb(from: MovieEntity) = Movie(
        from.id,
        from.title,
        from.overview,
        from.releaseDate,
        from.backdropPath
    )

    fun toDb(from: Movie) = MovieEntity(
        from.id,
        from.overview,
        from.title,
        from.releaseDate,
        from.backdropPath
    )
}