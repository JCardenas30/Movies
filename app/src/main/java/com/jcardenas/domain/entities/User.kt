package com.jcardenas.domain.entities

import com.jcardenas.domain.common.ListItemViewModel

data class User(
    val employeeNumber: String,
    val fullName: String,
    val phone: String,
    val email: String,
    val address: String
): ListItemViewModel()