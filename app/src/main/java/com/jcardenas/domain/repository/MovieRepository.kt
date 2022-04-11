package com.jcardenas.domain.repository

import com.jcardenas.domain.common.Result
import com.jcardenas.domain.entities.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getRemoteMovies(): Result<List<Movie>>
    suspend fun getMovies(): Flow<List<Movie>>
    suspend fun insertMovies(movies: List<Movie>)
}