package com.jcardenas.domain.usecases

import com.jcardenas.domain.repository.ImageRepository
import javax.inject.Inject

class ListAllImageUseCase @Inject constructor(
    private val imageRepository: ImageRepository
) {
    suspend fun listAll() = imageRepository.listAll()
}