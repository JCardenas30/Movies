package com.jcardenas.data.repositories.users

import com.google.firebase.storage.StorageReference
import com.jcardenas.domain.entities.User
import com.jcardenas.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.jcardenas.domain.common.Result

class UserRepositoryImpl @Inject constructor(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource
): UserRepository {
    override suspend fun uploadAvatar(data: ByteArray, user: User): Flow<Result<StorageReference>> {
        return remoteDataSource.upload(data, user)
    }

    override suspend fun insert(user: User) {
        localDataSource.insert(user)
    }

    override fun all(): Flow<List<User>> {
        return localDataSource.all()
    }

}