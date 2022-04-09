package com.jcardenas.presentation.models

import com.jcardenas.presentation.helpers.ListItemViewModel

data class Movie(
    val id: Int,
    val name: String,
    val rating: Double,
    val description: String
): ListItemViewModel()