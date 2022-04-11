package com.jcardenas.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image")
data class ImageEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val date: String
)