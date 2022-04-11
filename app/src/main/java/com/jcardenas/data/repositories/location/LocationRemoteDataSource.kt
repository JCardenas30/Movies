package com.jcardenas.data.repositories.location

import com.jcardenas.domain.common.Result
import com.jcardenas.domain.entities.Location
import kotlinx.coroutines.flow.Flow

interface LocationRemoteDataSource {
    suspend fun getLocations(): Flow<Result<List<Location>>>
    suspend fun saveLocation(location: Location): Flow<Result<Boolean>>
}