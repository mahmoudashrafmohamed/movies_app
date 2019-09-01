package com.dev.mahmoud_ashraf.movies.di.components

import com.dev.mahmoud_ashraf.movies.di.modules.ApplicationModule
import com.dev.mahmoud_ashraf.movies.di.modules.DataModule
import com.dev.mahmoud_ashraf.movies.di.modules.NetworkModule
import com.dev.mahmoud_ashraf.movies.di.modules.ViewModelModule
import com.dev.mahmoud_ashraf.movies.di.scopes.MovieApplicationScope
import com.dev.mahmoud_ashraf.movies.presentation.details.DetailsActivity
import com.dev.mahmoud_ashraf.movies.presentation.movies.MainActivity
import dagger.Component

@MovieApplicationScope
@Component(modules = [
    NetworkModule::class,
    DataModule::class,
    ApplicationModule::class,
    ViewModelModule::class
])
interface ApplicationComponent {

    fun inject(mainActivity : MainActivity)
    fun inject(detailsActivity : DetailsActivity)

}