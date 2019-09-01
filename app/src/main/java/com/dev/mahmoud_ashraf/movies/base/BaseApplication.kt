package com.dev.mahmoud_ashraf.movies.base

import android.app.Application
import com.dev.mahmoud_ashraf.movies.di.components.ApplicationComponent
import com.dev.mahmoud_ashraf.movies.BuildConfig
import com.dev.mahmoud_ashraf.movies.di.components.DaggerApplicationComponent
import com.dev.mahmoud_ashraf.movies.di.modules.ApplicationModule
import com.dev.mahmoud_ashraf.movies.di.modules.DataModule
import com.dev.mahmoud_ashraf.movies.di.modules.NetworkModule
import timber.log.Timber

class BaseApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    @Suppress("DEPRECATION")
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        applicationComponent = DaggerApplicationComponent.builder()
            .networkModule(NetworkModule())
            .dataModule(DataModule(this))
            .applicationModule(ApplicationModule(this))
            .build()

    }

}