package com.dev.mahmoud_ashraf.movies.presentation.details

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.dev.mahmoud_ashraf.movies.R
import com.dev.mahmoud_ashraf.movies.base.BaseApplication
import com.dev.mahmoud_ashraf.movies.base.ViewModelFactory
import com.dev.mahmoud_ashraf.movies.data.model.Movie
import com.dev.mahmoud_ashraf.movies.presentation.BaseActivity
import com.dev.mahmoud_ashraf.movies.utils.GlideApp
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.content_details.*
import javax.inject.Inject

class DetailsActivity : BaseActivity()  {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: DetailsViewModel




    override fun injectActivity() {
        (application as? BaseApplication)?.applicationComponent?.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(toolbar)

        val movie = intent.getParcelableExtra<Movie>("movie")
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailsViewModel::class.java)
        fab.setOnClickListener { view ->
            viewModel.addToFavourite(movie)
            Snackbar.make(view, "favourite <3", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }



        title = movie?.title
        description.text = movie?.overview

        GlideApp.with(this)
            .load("https://image.tmdb.org/t/p/w200/" + movie?.backdropPath)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.color.colorPrimary)
            .error(R.color.colorPrimary)
            .centerCrop()
            .into(image)


    }


    private fun AddToFavourite(movie: Movie) {
        viewModel.addToFavourite(movie)
    }
}
