package com.jcardenas.data.repositories.movies

import com.jcardenas.domain.entities.Movie
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    suspend fun insert(movie: List<Movie>)
    suspend fun getMovies(): Flow<List<Movie>>
}