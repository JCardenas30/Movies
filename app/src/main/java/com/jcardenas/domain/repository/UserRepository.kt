package com.jcardenas.domain.repository

import com.google.firebase.storage.StorageReference
import com.jcardenas.domain.entities.User
import kotlinx.coroutines.flow.Flow
import com.jcardenas.domain.common.Result

interface UserRepository {
    suspend fun uploadAvatar(data: ByteArray, user: User): Flow<Result<StorageReference>>
    suspend fun insert(user: User)
    fun all(): Flow<List<User>>
}