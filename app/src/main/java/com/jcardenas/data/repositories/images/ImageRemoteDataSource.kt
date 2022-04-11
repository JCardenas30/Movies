package com.jcardenas.data.repositories.images

import com.google.firebase.storage.StorageReference
import com.jcardenas.domain.common.Result
import com.jcardenas.domain.entities.Image
import kotlinx.coroutines.flow.Flow

interface ImageRemoteDataSource {
    suspend fun upload(data: ByteArray, extension: String): Flow<Result<Image>>
    suspend fun listAll(): Flow<Result<List<Image>>>
}