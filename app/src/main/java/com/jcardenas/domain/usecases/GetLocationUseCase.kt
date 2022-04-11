package com.jcardenas.domain.usecases

import com.jcardenas.domain.repository.LocationRepository
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    suspend fun getLocations() = locationRepository.getLocations()
}