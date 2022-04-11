package com.jcardenas.data.repositories.images

import com.google.firebase.storage.FirebaseStorage
import com.jcardenas.domain.common.Constants.IMAGES_REF
import com.jcardenas.domain.common.DateUtil
import com.jcardenas.domain.common.Result
import com.jcardenas.domain.entities.Image
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ImageRemoteDataSourceImpl @Inject constructor(
    private val storage: FirebaseStorage
): ImageRemoteDataSource {

    override suspend fun upload(data: ByteArray, extension: String) = flow<Result<Image>> {
        val storageRef = storage.reference.child("$IMAGES_REF/${DateUtil.getCalendar().timeInMillis}$extension")
        storageRef.putBytes(data).await()
        emit(Result.Success(Image(storageRef)))
    }.catch {
        emit(Result.Error(Exception(it.message)))
    }

    override suspend fun listAll() = flow<Result<List<Image>>> {
        val storageRef = storage.reference.child(IMAGES_REF)
        val items = storageRef.listAll().await().items.map {
            Image(it)
        }
        emit(Result.Success(items))
    }.catch {
        emit(Result.Error(Exception(it.message)))
    }

}