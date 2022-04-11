package com.jcardenas.domain.entities

import com.jcardenas.domain.common.ListItemViewModel

data class Movie(
    val id: Int,
    val title: String,
    val description: String,
    val voteAverage: Double,
    val posterPath: String
): ListItemViewModel()