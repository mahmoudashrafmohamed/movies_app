package com.dev.mahmoud_ashraf.movies.di.modules

import com.dev.mahmoud_ashraf.movies.di.scopes.MovieApplicationScope
import com.dev.mahmoud_ashraf.movies.data.repository.remote.MovieAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
    internal fun provideHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        return httpClient.build()
    }



    @MovieApplicationScope
    @Provides
    internal fun provideRetrofit(httpClient: OkHttpClient): MovieAPI {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()
            .create(MovieAPI::class.java)
    }

}