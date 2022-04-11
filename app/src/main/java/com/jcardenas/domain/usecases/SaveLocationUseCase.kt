package com.jcardenas.domain.usecases

import com.jcardenas.domain.entities.Location
import com.jcardenas.domain.repository.LocationRepository
import javax.inject.Inject

class SaveLocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    suspend fun saveLocation(location: Location) = locationRepository.saveLocation(location)
}