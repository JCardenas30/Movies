package com.jcardenas.data.api

data class MovieResponse (
    val page: Int,
    val results: List<ItemMovieResponse>,
    val total_pages: Int,
    val total_results: Int
)

data class ItemMovieResponse(
    val id: Int,
    val original_title: String,
    val overview: String,
    val vote_average: Double,
    val poster_path: String
)