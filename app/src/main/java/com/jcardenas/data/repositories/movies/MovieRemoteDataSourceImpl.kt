package com.jcardenas.data.repositories.movies

import com.jcardenas.data.api.MoviesApi
import com.jcardenas.data.mappers.MovieApiResponseMapper
import com.jcardenas.domain.common.Result
import com.jcardenas.domain.entities.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val moviesApi: MoviesApi,
    private val mapper: MovieApiResponseMapper
): MovieRemoteDataSource {
    override suspend fun getPopular(): Result<List<Movie>> =
        withContext(Dispatchers.IO){
            try {
                val response = moviesApi.getPopulars()
                if (response.isSuccessful) {
                    return@withContext Result.Success(mapper.toMovieList(response.body()!!))
                } else {
                    return@withContext Result.Error(Exception(response.message()))
                }
            }catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }
}