package com.dev.mahmoud_ashraf.movies.data.repository.remote

import com.dev.mahmoud_ashraf.movies.data.response.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET("/3/movie/popular?api_key=6fd435cb71362c657e45c6cd8a5c0510")
    fun getMovies(@Query("page") pageNumber: Int): Single<MovieResponse>

}