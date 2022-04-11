package com.jcardenas.data.repositories.movies

import androidx.lifecycle.LiveData
import com.jcardenas.data.db.MovieDao
import com.jcardenas.data.mappers.MovieEntityMapper
import com.jcardenas.domain.entities.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieLocalDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val mapper: MovieEntityMapper
): MovieLocalDataSource {

    override suspend fun insert(movies: List<Movie>) = withContext(Dispatchers.IO){
        movieDao.insert(mapper.toMovieEntityList(movies))
    }

    override suspend fun getMovies(): Flow<List<Movie>> {
        val savedMovies = movieDao.getMovies()
        return savedMovies.map { list ->
            list.map {
                mapper.toMovie(it)
            }
        }
    }
}