package com.jcardenas.data.repositories.location

import com.google.firebase.firestore.FirebaseFirestore
import com.jcardenas.data.entities.LocationEntity
import com.jcardenas.domain.common.Constants
import com.jcardenas.domain.common.Result
import com.jcardenas.domain.entities.Location
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LocationRemoteDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
): LocationRemoteDataSource {
    override suspend fun getLocations() = flow<Result<List<Location>>> {
        val snapshot = firestore.collection(Constants.LOCATION_REF).get().await()
        val locations: List<Location> = snapshot.toObjects(LocationEntity::class.java).map {
            Location(
                latitude = it.latitude,
                longitude = it.longitude,
                date = it.date
            )
        }
        emit(Result.Success(locations))
    }.catch {
        emit(Result.Error(Exception(it.message)))
    }.flowOn(Dispatchers.IO)

    override suspend fun saveLocation(location: Location) = flow<Result<Boolean>> {
        firestore.collection(Constants.LOCATION_REF)
            .add(location)
            .await()
        emit(Result.Success(true))
    }.catch {
        emit(Result.Error(Exception(it.message)))
    }.flowOn(Dispatchers.IO)
}