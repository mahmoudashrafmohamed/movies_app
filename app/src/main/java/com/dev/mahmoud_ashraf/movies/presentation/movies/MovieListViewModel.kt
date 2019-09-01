package com.dev.mahmoud_ashraf.movies.presentation.movies

import androidx.lifecycle.ViewModel
import com.dev.mahmoud_ashraf.movies.domain.usecase.MovieUseCase
import javax.inject.Inject

class MovieListViewModel
@Inject
constructor(private val useCase: MovieUseCase)
    : ViewModel() {

    var moviesLiveData = useCase.moviesLiveData()


    fun fetchMovies(pageNumber: Int) = useCase.getMovies(pageNumber)

    fun fetchFavouriteMovies() = useCase.getFavMovies()

    fun cleanObservables() = useCase.clear()

    override fun onCleared() {
        super.onCleared()
        cleanObservables()
    }



}