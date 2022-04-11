package com.jcardenas.domain.usecases

import com.jcardenas.domain.entities.User
import com.jcardenas.domain.repository.UserRepository
import javax.inject.Inject

class UploadProfileUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun upload(data: ByteArray, user: User) = userRepository.uploadAvatar(data, user)
}