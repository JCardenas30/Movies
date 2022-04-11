package com.jcardenas.data.mappers

import com.jcardenas.data.api.MovieResponse
import com.jcardenas.domain.entities.Movie

class MovieApiResponseMapper {

    fun toMovieList(response: MovieResponse): List<Movie> {
        return response.results.map {
            Movie(
                id = it.id,
                title = it.original_title,
                description = it.overview,
                voteAverage = it.vote_average,
                posterPath = it.poster_path
            )
        }
    }
}