package com.jcardenas.data.repositories.users

import com.jcardenas.domain.entities.User
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {
    suspend fun insert(user: User)
    fun all(): Flow<List<User>>
}