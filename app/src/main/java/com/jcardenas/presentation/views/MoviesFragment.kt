package com.jcardenas.presentation.views

import android.content.DialogInterface
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.jcardenas.domain.entities.Movie
import com.jcardenas.movies.R
import com.jcardenas.movies.databinding.FragmentMoviesBinding
import com.jcardenas.presentation.helpers.GenericAdapter
import com.jcardenas.presentation.vm.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment: BaseFragment<FragmentMoviesBinding>() {

    private val viewModel: MoviesViewModel by viewModels()
    private val adapter = GenericAdapter<Movie>(R.layout.item_movie)

    override fun getLayout(): Int = R.layout.fragment_movies
    override fun bindViewToModel() {
        binding.viewModel = viewModel
    }

    override fun setupUI() {
        setTitle(R.string.menu_movies)
        binding.rvMovies.adapter = adapter
        viewModel.getMovies()
        addObservers()
    }

    private fun addObservers(){
        viewModel.error.observe(this, Observer {
            if(it) {
                showError(DialogInterface.OnClickListener { dialog, _ ->
                    dialog.dismiss()
                })
            }
        })

        viewModel.data.observe(this, Observer {
            adapter.addItems(it)
        })
    }
}