package com.jcardenas.data.repositories.location

import com.jcardenas.domain.entities.Location
import com.jcardenas.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.jcardenas.domain.common.Result

class LocationRepositoryImpl @Inject constructor(
    private val locationRemoteDataSource: LocationRemoteDataSource
): LocationRepository {
    override suspend fun getLocations(): Flow<Result<List<Location>>> {
        return locationRemoteDataSource.getLocations()
    }

    override suspend fun saveLocation(location: Location): Flow<Result<Boolean>> {
        return locationRemoteDataSource.saveLocation(location)
    }

}