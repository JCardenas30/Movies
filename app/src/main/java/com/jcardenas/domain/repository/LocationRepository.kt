package com.jcardenas.domain.repository

import com.jcardenas.domain.entities.Location
import kotlinx.coroutines.flow.Flow
import com.jcardenas.domain.common.Result

interface LocationRepository {
    suspend fun getLocations(): Flow<Result<List<Location>>>
    suspend fun saveLocation(location: Location): Flow<Result<Boolean>>
}