package com.jcardenas.data.repositories.images

import com.jcardenas.domain.common.Result
import com.jcardenas.domain.entities.Image
import com.jcardenas.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val imageRemoteDataSource: ImageRemoteDataSource
): ImageRepository {
    override suspend fun upload(data: ByteArray, extension: String): Flow<Result<Image>> {
        return imageRemoteDataSource.upload(data, extension)
    }

    override suspend fun listAll(): Flow<Result<List<Image>>> {
        return imageRemoteDataSource.listAll()
    }

}