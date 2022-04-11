package com.jcardenas.domain.usecases

import com.jcardenas.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    fun all() = userRepository.all()
}