package com.jcardenas.data.repositories.users

import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.jcardenas.domain.common.Constants
import com.jcardenas.domain.common.Result
import com.jcardenas.domain.entities.User
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val storage: FirebaseStorage
): UserRemoteDataSource {
    override suspend fun upload(data: ByteArray, user: User) = flow<Result<StorageReference>> {
        val storageRef = storage.reference.child("${Constants.PROFILES_REF}/${user.employeeNumber}.jpg")
        storageRef.putBytes(data).await()
        emit(Result.Success(storageRef))
    }.catch {
        emit(Result.Error(Exception(it.message)))
    }
}