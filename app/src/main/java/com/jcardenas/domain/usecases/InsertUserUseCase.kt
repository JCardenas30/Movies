package com.jcardenas.domain.usecases

import com.jcardenas.domain.entities.User
import com.jcardenas.domain.repository.UserRepository
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun insert(user: User) = userRepository.insert(user)
}