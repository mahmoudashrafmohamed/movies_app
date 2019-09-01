package com.dev.mahmoud_ashraf.movies.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(

    val id: Int? = null,
    val title: String? = null,
    val overview: String? = null,
    val releaseDate: String? = null,
    val backdropPath: String? = null

) : Parcelable