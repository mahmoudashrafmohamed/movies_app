package com.dev.mahmoud_ashraf.movies.domain.usecase

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dev.mahmoud_ashraf.movies.data.model.Movie
import com.dev.mahmoud_ashraf.movies.data.repository.MoviesRepo
import com.dev.mahmoud_ashraf.movies.domain.NetworkState
import com.dev.mahmoud_ashraf.movies.domain.responseresult.MoviesResponseResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MovieUseCase
@Inject
constructor(private val moviesRepo: MoviesRepo) {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val moviesResponseResult: MutableLiveData<MoviesResponseResult> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun getMovies(pageNumber: Int) {
        if (compositeDisposable.isDisposed)
            compositeDisposable = CompositeDisposable()
        compositeDisposable.add(moviesRepo.getMovies(pageNumber)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe
            {
                moviesResponseResult.postValue(MoviesResponseResult(NetworkState.LOADING))
            }
            .subscribe(
                { moviesList ->
                    moviesList?.let {
                        moviesResponseResult.postValue(MoviesResponseResult(NetworkState.LOADED, it))
                    } ?: run {
                        moviesResponseResult.postValue(MoviesResponseResult(NetworkState.error("data is finished!")))
                    }
                },
                { throwable ->
                    moviesResponseResult.postValue(MoviesResponseResult(NetworkState.error(
                        throwable.message ?: "Something Went Wrong!")))
                }
            )
        )
    }


    @SuppressLint("CheckResult")
    fun getFavMovies() {
        if (compositeDisposable.isDisposed)
            compositeDisposable = CompositeDisposable()
        compositeDisposable.add(moviesRepo.getFavMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe
            {
                moviesResponseResult.postValue(MoviesResponseResult(NetworkState.LOADING))
            }
            .subscribe(
                { moviesList ->
                    moviesList?.let {
                        moviesResponseResult.postValue(MoviesResponseResult(NetworkState.LOADED, it))
                    } ?: run {
                        moviesResponseResult.postValue(MoviesResponseResult(NetworkState.error("data is finished!")))
                    }
                },
                { throwable ->
                    moviesResponseResult.postValue(MoviesResponseResult(NetworkState.error(
                        throwable.message ?: "Something Went Wrong!")))
                }
            )
        )
    }



    fun addToFav(movie: Movie)  {


        if (compositeDisposable.isDisposed)
            compositeDisposable = CompositeDisposable()
        compositeDisposable.add(moviesRepo.addToFavourite(movie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe
            {
                moviesResponseResult.postValue(MoviesResponseResult(NetworkState.LOADING))
            }

            .subscribe(
                {

                    /**moviesList ->
                    moviesList?.let {
                        moviesResponseResult.postValue(MoviesResponseResult(NetworkState.LOADED, it))
                    } ?: run {
                        moviesResponseResult.postValue(MoviesResponseResult(NetworkState.error("data is finished!")))
                    }*/
                },
                { throwable ->
                    moviesResponseResult.postValue(MoviesResponseResult(NetworkState.error(
                        throwable.message ?: "Something Went Wrong!")))
                }
            )
        )



    }


    fun moviesLiveData(): LiveData<MoviesResponseResult> = moviesResponseResult



    fun clear() {
        compositeDisposable.clear()
    }




}