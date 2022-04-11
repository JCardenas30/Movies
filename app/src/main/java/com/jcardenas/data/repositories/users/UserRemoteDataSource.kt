package com.jcardenas.data.repositories.users

import com.google.firebase.storage.StorageReference
import com.jcardenas.domain.common.Result
import com.jcardenas.domain.entities.User
import kotlinx.coroutines.flow.Flow

interface UserRemoteDataSource {
    suspend fun upload(data: ByteArray, user: User): Flow<Result<StorageReference>>
}