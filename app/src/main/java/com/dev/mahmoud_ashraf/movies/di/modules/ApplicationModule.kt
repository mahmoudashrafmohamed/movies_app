package com.dev.mahmoud_ashraf.movies.di.modules

import android.content.Context
import com.dev.mahmoud_ashraf.movies.di.scopes.MovieApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val context: Context) {

    @Provides
    @MovieApplicationScope
    internal fun provideContext() = context
}