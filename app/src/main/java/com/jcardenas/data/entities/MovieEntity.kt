package com.jcardenas.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val voteAverage: Double,
    val posterPath: String
)