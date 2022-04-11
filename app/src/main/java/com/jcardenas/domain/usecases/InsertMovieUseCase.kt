package com.jcardenas.domain.usecases

import com.jcardenas.domain.entities.Movie
import com.jcardenas.domain.repository.MovieRepository
import javax.inject.Inject

class InsertMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend fun insert(movies: List<Movie>) = movieRepository.insertMovies(movies)
}