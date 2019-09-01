package com.dev.mahmoud_ashraf.movies.data.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dev.mahmoud_ashraf.movies.data.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class DatabaseManager : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {

        fun getInstance(context: Context): DatabaseManager {
            return Room.databaseBuilder(context.applicationContext,
                DatabaseManager::class.java, "movie.db")
                .build()
        }

    }
}