package com.dev.mahmoud_ashraf.movies.di.modules

import android.content.Context
import com.dev.mahmoud_ashraf.movies.data.repository.MoviesRepo
import com.dev.mahmoud_ashraf.movies.data.repository.local.DatabaseManager
import com.dev.mahmoud_ashraf.movies.data.repository.local.MovieDao
import com.dev.mahmoud_ashraf.movies.data.repository.remote.MovieAPI
import com.dev.mahmoud_ashraf.movies.domain.usecase.MovieUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class DataModule
@Inject constructor(context: Context) {

    private val databaseManager: DatabaseManager = DatabaseManager.getInstance(context)

    @Provides

    internal fun provideMovieDao()
            = databaseManager.movieDao()

    @Provides
    internal fun provideRepository(local: MovieDao, remote: MovieAPI)
            = MoviesRepo(local, remote)

    @Provides
    internal fun providesMovieUseCase(moviesRepo: MoviesRepo)
            = MovieUseCase(moviesRepo)

}