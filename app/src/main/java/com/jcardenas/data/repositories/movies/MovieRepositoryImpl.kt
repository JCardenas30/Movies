package com.jcardenas.data.repositories.movies

import com.jcardenas.domain.common.Result
import com.jcardenas.domain.entities.Movie
import com.jcardenas.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieRemoteDataSource: MovieRemoteDataSource
): MovieRepository {
    override suspend fun getRemoteMovies(): Result<List<Movie>> {
        return movieRemoteDataSource.getPopular()
    }

    override suspend fun getMovies(): Flow<List<Movie>> {
        return movieLocalDataSource.getMovies()
    }

    override suspend fun insertMovies(movies: List<Movie>) {
        movieLocalDataSource.insert(movies)
    }

}