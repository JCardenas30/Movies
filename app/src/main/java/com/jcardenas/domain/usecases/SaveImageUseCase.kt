package com.jcardenas.domain.usecases

import com.jcardenas.domain.repository.ImageRepository
import javax.inject.Inject

class SaveImageUseCase @Inject constructor(
    private val imageRepository: ImageRepository
) {
    suspend fun upload(data: ByteArray, extension: String) = imageRepository.upload(data, extension)
}