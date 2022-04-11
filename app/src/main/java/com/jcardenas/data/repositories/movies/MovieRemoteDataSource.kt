package com.jcardenas.data.repositories.movies

import com.jcardenas.domain.common.Result
import com.jcardenas.domain.entities.Movie

interface MovieRemoteDataSource {
    suspend fun getPopular(): Result<List<Movie>>
}