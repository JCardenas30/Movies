package com.jcardenas.data.mappers

import com.jcardenas.data.entities.MovieEntity
import com.jcardenas.domain.entities.Movie

class MovieEntityMapper {

    fun toMovieEntityList(movies: List<Movie>): List<MovieEntity> {
        return movies.map {
            toMovieEntity(it)
        }
    }

    fun toMovieEntity(movie: Movie): MovieEntity {
        return MovieEntity(
            id = movie.id,
            title = movie.title,
            description = movie.description,
            voteAverage = movie.voteAverage,
            posterPath = movie.posterPath
        )
    }

    fun toMovie(entity: MovieEntity): Movie {
        return Movie(
            id = entity.id,
            title = entity.title,
            description = entity.description,
            voteAverage = entity.voteAverage,
            posterPath = entity.posterPath
        )
    }
}