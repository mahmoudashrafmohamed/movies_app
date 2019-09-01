package com.dev.mahmoud_ashraf.movies.data.repository.local

import androidx.room.*
import com.dev.mahmoud_ashraf.movies.data.model.MovieEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MovieDao {

    @get:Query("SELECT * FROM movie ORDER BY title ASC")
    val getMovies: Single<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE id = :movieId")
    fun getMovie(movieId: Int): Single<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(entity: MovieEntity): Completable

    @Delete
    fun delete(entity: MovieEntity)

    @Query("DELETE FROM movie")
    fun deleteAll(): Int

}