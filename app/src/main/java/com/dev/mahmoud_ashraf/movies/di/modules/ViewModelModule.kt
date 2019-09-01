package com.dev.mahmoud_ashraf.movies.di.modules

import androidx.lifecycle.ViewModel
import com.dev.mahmoud_ashraf.movies.di.scopes.ViewModelKey
import com.dev.mahmoud_ashraf.movies.presentation.details.DetailsViewModel
import com.dev.mahmoud_ashraf.movies.presentation.movies.MovieListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    abstract fun bindMovieListViewModel(viewModel: MovieListViewModel): ViewModel



    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindDetailsViewModel(viewModel: DetailsViewModel): ViewModel



}