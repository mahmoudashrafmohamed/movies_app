package com.dev.mahmoud_ashraf.movies.data.repository

import com.dev.mahmoud_ashraf.movies.data.mapper.MovieMapper
import com.dev.mahmoud_ashraf.movies.data.model.Movie
import com.dev.mahmoud_ashraf.movies.data.repository.local.MovieDao
import com.dev.mahmoud_ashraf.movies.data.repository.remote.MovieAPI
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class MoviesRepo
@Inject
constructor(private val local: MovieDao,
            private val remote: MovieAPI
) {

    private val mapper = MovieMapper()

    fun getMovies(pageNumber: Int): Flowable<List<Movie>?> {


        val remoteMovie =
            remote.getMovies(pageNumber).map { movieResponse ->
                movieResponse.results?.map { movieEntity ->
                    if (pageNumber == 1)
                        local.insertMovie(movieEntity)
                    mapper.fromDb(movieEntity)
                }
            }


        return remoteMovie.toFlowable()
    }


    fun getFavMovies(): Flowable<List<Movie>?> {

        val localMovie =
            local.getMovies.map { movieEntityList ->
                movieEntityList.map { movieEntity ->
                    mapper.fromDb(movieEntity)
                }
            }


        return localMovie.toFlowable()
    }



    fun addToFavourite(movie: Movie) = local.insertMovie(mapper.toDb(movie))





    @Suppress("unused")
    fun clearDatabase(): Single<Int> {
        return Observable.fromCallable { local.deleteAll() }.firstOrError()
    }



}