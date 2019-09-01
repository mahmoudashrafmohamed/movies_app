package com.dev.mahmoud_ashraf.movies.presentation.details

import androidx.lifecycle.ViewModel
import com.dev.mahmoud_ashraf.movies.data.model.Movie
import com.dev.mahmoud_ashraf.movies.domain.usecase.MovieUseCase
import javax.inject.Inject

class DetailsViewModel
@Inject
constructor(private val useCase: MovieUseCase)
    : ViewModel() {

    fun addToFavourite(movie: Movie) = useCase.addToFav(movie)

    private fun cleanObservables() = useCase.clear()

    override fun onCleared() {
        super.onCleared()
        cleanObservables()
    }



}