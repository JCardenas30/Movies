package com.jcardenas.domain.repository

import com.jcardenas.domain.common.Result
import com.jcardenas.domain.entities.Image
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    suspend fun upload(data: ByteArray, extension: String): Flow<Result<Image>>
    suspend fun listAll(): Flow<Result<List<Image>>>
}