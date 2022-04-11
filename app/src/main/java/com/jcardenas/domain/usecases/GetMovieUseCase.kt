package com.jcardenas.domain.usecases

import com.jcardenas.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend fun remote() = movieRepository.getRemoteMovies()
    suspend fun local() = movieRepository.getMovies()
}