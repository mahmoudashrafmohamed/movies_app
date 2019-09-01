package com.dev.mahmoud_ashraf.movies.presentation.movies

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dev.mahmoud_ashraf.movies.R
import com.dev.mahmoud_ashraf.movies.base.BaseApplication
import com.dev.mahmoud_ashraf.movies.base.ViewModelFactory
import com.dev.mahmoud_ashraf.movies.domain.NetworkState
import com.dev.mahmoud_ashraf.movies.presentation.BaseActivity
import com.dev.mahmoud_ashraf.movies.presentation.details.DetailsActivity
import com.dev.mahmoud_ashraf.movies.presentation.movies.adapter.MovieAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MovieListViewModel
    private lateinit var adapter: MovieAdapter


    override fun injectActivity() {
        (application as? BaseApplication)?.applicationComponent?.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieListViewModel::class.java)

        initAdapter()
        initSwipeToRefresh()
        observeMoviesList()
        fetchMovies()

    }

    private fun initAdapter() {
        adapter = MovieAdapter(ArrayList()) { movie ->

            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("movie", movie)
            startActivity(intent)

        }
        movies_list_view.adapter = adapter

    }

    private fun initSwipeToRefresh() {
        movies_swipe_refresh_layout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent)

        movies_swipe_refresh_layout.setOnRefreshListener {
            fetchMovies()
        }
    }

    private fun observeMoviesList() {
        viewModel.moviesLiveData.observe(this, Observer { moviesLiveData ->
            movies_swipe_refresh_layout.post {
                movies_swipe_refresh_layout.isRefreshing =
                    moviesLiveData.networkState.status == NetworkState.LOADING.status
            }
            when (moviesLiveData.networkState) {
                NetworkState.LOADED -> {
                    adapter.add(moviesLiveData.movies)
                    showEmptyList(moviesLiveData.movies?.isEmpty() ?: false)
                }
                NetworkState.LOADING -> {
                    // Loading
                }
                else -> {
                    showEmptyList(moviesLiveData.networkState.message != null, moviesLiveData.networkState.message)
                }
            }
        })

    }

    private fun fetchMovies(pageNumber: Int = 1) {
        adapter.clear()
        viewModel.fetchMovies(pageNumber)
    }
    private fun fetchFavouriteMovies() {
        adapter.clear()
        viewModel.fetchFavouriteMovies()
    }


    private fun showEmptyList(show: Boolean, message: String? = null) {
        message?.let {
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
            empty_list.text = it
        }
        if (show && adapter.getMovies().isEmpty()) {
            empty_list.visibility = View.VISIBLE
            movies_list_view.visibility = View.GONE
        } else {
            empty_list.visibility = View.GONE
            movies_list_view.visibility = View.VISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_details, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_fav -> {
           fetchFavouriteMovies()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        viewModel.cleanObservables()
    }
}