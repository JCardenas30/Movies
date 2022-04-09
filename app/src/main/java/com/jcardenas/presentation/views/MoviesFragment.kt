package com.jcardenas.presentation.views

import com.jcardenas.movies.R
import com.jcardenas.movies.databinding.FragmentMoviesBinding
import com.jcardenas.presentation.vm.MoviesViewModel

class MoviesFragment: BaseFragment<MoviesViewModel, FragmentMoviesBinding>() {

    override fun getLayout(): Int = R.layout.fragment_movies
    override fun getViewModelClass(): Class<MoviesViewModel> = MoviesViewModel::class.java

    override fun setupUI() {
        setTitle(R.string.menu_movies)
    }

    fun setListeners(){

    }
}