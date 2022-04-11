package com.jcardenas.data.api

import com.jcardenas.domain.common.Constants
import retrofit2.Response
import retrofit2.http.GET

interface MoviesApi {

    @GET("/3/discover/movie?sort_by=popularity.desc&api_key=${Constants.API_KEY}")
    suspend fun getPopulars(): Response<MovieResponse>
}