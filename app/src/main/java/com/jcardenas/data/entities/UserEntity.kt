package com.jcardenas.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val employeeNumber: String,
    val fullName: String,
    val phone: String,
    val email: String,
    val address: String
)